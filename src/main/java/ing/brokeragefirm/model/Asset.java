package ing.brokeragefirm.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Customer customer;

    private String assetName; // Hisse adı, TRY de bir varlık olarak tutulacak
    private int size;
    private int usableSize; // Kullanılabilir varlık
}
