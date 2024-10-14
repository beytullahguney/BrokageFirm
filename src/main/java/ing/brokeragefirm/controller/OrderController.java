package ing.brokeragefirm.controller;

import ing.brokeragefirm.model.Order;
import ing.brokeragefirm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@RequestParam Long customerId, @RequestParam String assetName,
                                             @RequestParam String side, @RequestParam int size,
                                             @RequestParam BigDecimal price) {
        Order order = orderService.createOrder(customerId, assetName, side, size, price);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Order>> listOrders(@RequestParam Long customerId, @RequestParam String startDate,
                                                  @RequestParam String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime start = LocalDateTime.parse(startDate, formatter);
        LocalDateTime end = LocalDateTime.parse(endDate, formatter);
        List<Order> orders = orderService.listOrders(customerId, start, end);
        return ResponseEntity.ok(orders);
    }

    @PostMapping("/cancel")
    public ResponseEntity<String> cancelOrder(@RequestParam Long orderId) {
        orderService.cancelOrder(orderId);
        return ResponseEntity.ok("Order canceled");
    }
}
