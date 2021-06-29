package guru.springframework.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String description;

    @ManyToOne
    private Recipe recipe;

    @NonNull
    private BigDecimal amount;

    @OneToOne(fetch = FetchType.EAGER)
    @NonNull
    private UnitOfMeasure unit;

    public Ingredient() {

    }

    protected boolean canEqual(final Object other) {
        return other instanceof Ingredient;
    }

}
