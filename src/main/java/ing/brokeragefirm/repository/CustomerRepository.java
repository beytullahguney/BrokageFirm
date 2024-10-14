package ing.brokeragefirm.repository;

import ing.brokeragefirm.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
