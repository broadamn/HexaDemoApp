package HexagonalArchitectureDemoApplication;

import HexagonalArchitectureDemoApplication.adapter.in.web.dto.ComponentInDTO;
import HexagonalArchitectureDemoApplication.application.port.in.ComponentInputPort;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@AllArgsConstructor
public class DemoHexagonalApplication {

    private final ComponentInputPort componentInputPort;


    public static void main(String[] args) {
        SpringApplication.run(DemoHexagonalApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo() {
        return (args) -> {
            // Adding components to the database
                ComponentInDTO component1 = new ComponentInDTO("Cpu", "Quite good", 19.99, 100, "Electronics");
            ComponentInDTO component2 = new ComponentInDTO("Component 2", "Description 2", 29.99, 50, "Electronics");

            // Upload components using the input port
            componentInputPort.uploadComponent(component1);
            componentInputPort.uploadComponent(component2);

            // Retrieve and display all components
            System.out.println("All Components:");
            componentInputPort.getAllComponents().forEach(System.out::println);

            // Purchase a component (you may want to pass a valid ID)
            componentInputPort.purchaseComponent(1, 105);

            // Display components after purchase
            System.out.println("Components after purchase:");
            componentInputPort.getAllComponents().forEach(System.out::println);
        };
    }
}

