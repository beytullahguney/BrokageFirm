package ing.brokeragefirm.repository;

import ing.brokeragefirm.model.Asset;
import ing.brokeragefirm.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AssetRepository extends JpaRepository<Asset, Long> {
    Optional<Asset> findByCustomerAndAssetName(Customer customer, String assetName);

    List<Asset> findByCustomer(Customer customer);
}
