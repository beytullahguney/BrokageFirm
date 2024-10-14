package ing.brokeragefirm.service;

import ing.brokeragefirm.model.Asset;
import ing.brokeragefirm.model.Customer;
import ing.brokeragefirm.repository.AssetRepository;
import ing.brokeragefirm.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AssetService {

    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public Asset depositMoney(Long customerId, Integer amount) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        Asset asset = assetRepository.findByCustomerAndAssetName(customer, "TRY")
                .orElse(new Asset());

        if (asset.getId() == null) {
            asset.setCustomer(customer);
            asset.setAssetName("TRY");
            asset.setSize(amount);
            asset.setUsableSize(amount);
        } else {
            asset.setSize(asset.getSize() + amount);
            asset.setUsableSize(asset.getUsableSize() + amount);
        }

        return assetRepository.save(asset);
    }

    public Asset withdrawMoney(Long customerId, Integer amount) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        Asset asset = assetRepository.findByCustomerAndAssetName(customer, "TRY")
                .orElseThrow(() -> new IllegalArgumentException("TRY asset not found"));

        //TODO: type casting yapılacak
        if (asset.getUsableSize() < amount) {
            throw new IllegalArgumentException("Insufficient TRY balance");
        }

        asset.setUsableSize(asset.getUsableSize() - amount);

        return assetRepository.save(asset);
    }

    public List<Asset> listAssets(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));
        return assetRepository.findByCustomer(customer);
    }
}