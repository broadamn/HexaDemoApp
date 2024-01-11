package HexagonalArchitectureDemoApplication.adapter.in.web;

import HexagonalArchitectureDemoApplication.adapter.in.web.dto.ComponentInDTO;
import HexagonalArchitectureDemoApplication.adapter.in.web.dto.ComponentOutDTO;
import HexagonalArchitectureDemoApplication.application.port.in.ComponentInputPort;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@AllArgsConstructor
@RestController
@RequestMapping("/api/pccomponents")
public class WebAdapter {

    private final ComponentInputPort componentInputPort;

    @GetMapping
    public Collection<ComponentOutDTO> getAllComponents() {
        return componentInputPort.getAllComponents();
    }

    @GetMapping("/{componentId}")
    public ComponentOutDTO getComponentById(@PathVariable Integer componentId) {
        return componentInputPort.getById(componentId);
    }

    @PostMapping("/purchase/{componentId}")
    public String purchaseComponent(@PathVariable Integer componentId, @RequestParam Integer amount) {
        if (componentInputPort.purchaseComponent(componentId, amount)){
            return "You have purchased " + amount.toString() + " piece(s) of component with id "
                    + componentId.toString();
        }
        else return "There are not available " + amount.toString() + " components";
    }

    @PostMapping
    public void uploadComponent(@RequestBody @Valid ComponentInDTO componentInDTO) {
        componentInputPort.uploadComponent(componentInDTO);
    }
}
