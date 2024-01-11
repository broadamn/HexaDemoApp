package HexagonalArchitectureDemoApplication.adapter.in.web.dto;

import lombok.Data;

@Data
public class ComponentOutDTO {
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private String category;
}
