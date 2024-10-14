package ing.brokeragefirm.repository;

import ing.brokeragefirm.model.Customer;
import ing.brokeragefirm.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomerAndStatus(Customer customer, String status);
}
