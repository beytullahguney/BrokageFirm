import ing.brokeragefirm.BrokerageApplication;
import ing.brokeragefirm.model.Asset;
import ing.brokeragefirm.model.Customer;
import ing.brokeragefirm.repository.CustomerRepository;
import ing.brokeragefirm.service.AssetService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = BrokerageApplication.class)
public class AssetServiceTests {

    @Autowired
    private AssetService assetService;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testDepositMoney() {
        // Müşteri oluştur
        Customer customer = new Customer();
        customer.setName("Test Customer");
        customerRepository.save(customer);

        // TRY varlığına para yatır
        Asset asset = assetService.depositMoney(customer.getId(), 5000);

        assertNotNull(asset);
        assertEquals("TRY", asset.getAssetName());
        assertEquals(5000, asset.getSize());
    }

    @Test
    public void testWithdrawMoney() {
        // Müşteri ve TRY varlığı oluştur
        Customer customer = new Customer();
        customer.setName("Test Customer");
        customerRepository.save(customer);

        Asset asset = assetService.depositMoney(customer.getId(), 10000);

        // Para çekme işlemi yap
        asset = assetService.withdrawMoney(customer.getId(), 5000);

        assertEquals(5000, asset.getUsableSize());
    }
}