package HexagonalArchitectureDemoApplication.adapter.in.web.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ComponentInDTO {

    @NotBlank
    @Size(max = 200)
    private String name;

    @NotBlank
    private String description;

    @NotNull
    @Positive
    private Double price;

    @NotNull
    @Positive
    private Integer stock;

    @Pattern(regexp = "^[a-zA-Z]+$", message = "field should contain only letters.")
    @NotBlank
    @Size(max = 200)
    private String category;
}
