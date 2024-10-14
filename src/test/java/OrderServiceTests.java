import ing.brokeragefirm.model.Asset;
import ing.brokeragefirm.model.Customer;
import ing.brokeragefirm.model.Order;
import ing.brokeragefirm.repository.AssetRepository;
import ing.brokeragefirm.repository.CustomerRepository;
import ing.brokeragefirm.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class OrderServiceTests {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AssetRepository assetRepository;

    @Test
    public void testCreateOrder() {
        // Müşteri ve TRY varlığını oluştur
        Customer customer = new Customer();
        customer.setName("Test Customer");
        customerRepository.save(customer);

        Asset asset = new Asset();
        asset.setCustomer(customer);
        asset.setAssetName("TRY");
        asset.setSize(10000);
        asset.setUsableSize(10000);
        assetRepository.save(asset);

        // BUY emri oluştur
        Order order = orderService.createOrder(customer.getId(), "ABC", "BUY", 100, BigDecimal.valueOf(100));

        assertNotNull(order);
        assertEquals("PENDING", order.getStatus());
    }

    @Test
    public void testCancelOrder() {
        // Müşteri ve TRY varlığını oluştur
        Customer customer = new Customer();
        customer.setName("Test Customer");
        customerRepository.save(customer);

        Asset asset = new Asset();
        asset.setCustomer(customer);
        asset.setAssetName("TRY");
        asset.setSize(10000);
        asset.setUsableSize(10000);
        assetRepository.save(asset);

        // BUY emri oluştur
        Order order = orderService.createOrder(customer.getId(), "ABC", "BUY", 100, BigDecimal.valueOf(100));

        // Emri iptal et
        orderService.cancelOrder(order.getId());

        // İptal edilen emrin durumu CANCELED olmalı
        assertEquals("CANCELED", order.getStatus());
    }
}