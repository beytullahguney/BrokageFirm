package ing.brokeragefirm.service;

import ing.brokeragefirm.model.Asset;
import ing.brokeragefirm.model.Customer;
import ing.brokeragefirm.model.Order;
import ing.brokeragefirm.repository.AssetRepository;
import ing.brokeragefirm.repository.CustomerRepository;
import ing.brokeragefirm.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public Order createOrder(Long customerId, String assetName, String side, int size, BigDecimal price) {
        // Müşteri ve varlık kontrolü
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        Asset asset = assetRepository.findByCustomerAndAssetName(customer, "TRY")
                .orElseThrow(() -> new IllegalArgumentException("TRY asset not found for customer"));

        if (side.equals("BUY")) {
            // Yeterli TRY bakiyesi olup olmadığını kontrol et
            if (asset.getUsableSize() < size * price.intValue()) {
                throw new IllegalArgumentException("Insufficient TRY balance");
            }
            asset.setUsableSize(asset.getUsableSize() - size * price.intValue()); // TRY bakiyesini güncelle
        } else if (side.equals("SELL")) {
            Asset sellAsset = assetRepository.findByCustomerAndAssetName(customer, assetName)
                    .orElseThrow(() -> new IllegalArgumentException("Asset not found for customer"));
            // Yeterli varlık olup olmadığını kontrol et
            if (sellAsset.getUsableSize() < size) {
                throw new IllegalArgumentException("Insufficient asset size");
            }
            sellAsset.setUsableSize(sellAsset.getUsableSize() - size); // Varlık boyutunu güncelle
        }

        // Emir oluştur ve kaydet
        Order order = new Order();
        order.setCustomer(customer);
        order.setAssetName(assetName);
        order.setOrderSide(side);
        order.setSize(size);
        order.setPrice(price);
        order.setStatus("PENDING");
        order.setCreateDate(LocalDateTime.now());

        assetRepository.save(asset); // TRY bakiyesi güncellendiği için kaydediyoruz
        orderRepository.save(order);
        return order;
    }

    public List<Order> listOrders(Long customerId, LocalDateTime startDate, LocalDateTime endDate) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        return orderRepository.findByCustomerAndStatus(customer, "PENDING")
                .stream()
                .filter(order -> order.getCreateDate().isAfter(startDate) && order.getCreateDate().isBefore(endDate))
                .collect(Collectors.toList());
    }

    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        if (!order.getStatus().equals("PENDING")) {
            throw new IllegalArgumentException("Only PENDING orders can be canceled");
        }

        // Varlık miktarını geri yükle
        if (order.getOrderSide().equals("BUY")) {
            Asset asset = assetRepository.findByCustomerAndAssetName(order.getCustomer(), "TRY")
                    .orElseThrow(() -> new IllegalArgumentException("TRY asset not found"));
            asset.setUsableSize(asset.getUsableSize() + order.getSize() * order.getPrice().intValue());
            assetRepository.save(asset);
        } else if (order.getOrderSide().equals("SELL")) {
            Asset asset = assetRepository.findByCustomerAndAssetName(order.getCustomer(), order.getAssetName())
                    .orElseThrow(() -> new IllegalArgumentException("Asset not found"));
            asset.setUsableSize(asset.getUsableSize() + order.getSize());
            assetRepository.save(asset);
        }

        order.setStatus("CANCELED");
        orderRepository.save(order);
    }
}