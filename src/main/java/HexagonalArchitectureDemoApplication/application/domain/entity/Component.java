package HexagonalArchitectureDemoApplication.application.domain.entity;

import HexagonalArchitectureDemoApplication.application.domain.exception.InsuficientStock;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Component {

    private Integer id;
    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private String category;

    @Override
    public String toString() {
        return "Component{"
                + "id='" + id + '\''
                + ", name='" + name + '\''
                + ", description='" + description + '\''
                + ", price=" + price
                + ", stock=" + stock
                + ", category='" + category + '\''
                + "}\n";
    }

    public void decreaseStock(Integer amount) {
        if (this.stock != null && this.stock >= amount) {
            this.stock -= amount;
        } else {
            throw new InsuficientStock("Insufficient stock");
        }
    }

}
