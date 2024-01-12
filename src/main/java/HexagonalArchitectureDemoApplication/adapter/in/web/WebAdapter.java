package HexagonalArchitectureDemoApplication.adapter.in.web;

import HexagonalArchitectureDemoApplication.adapter.in.web.dto.ComponentInDTO;
import HexagonalArchitectureDemoApplication.adapter.in.web.dto.ComponentOutDTO;
import HexagonalArchitectureDemoApplication.application.port.in.ComponentInputPort;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@AllArgsConstructor
@Controller
@RequestMapping("/")
public class WebAdapter {

    private final ComponentInputPort componentInputPort;

    @GetMapping
    public String getAllComponents(Model model) {
        Collection<ComponentOutDTO> components = componentInputPort.getAllComponents();
        model.addAttribute("components", components);
        return "ComponentsPage";
    }

    @GetMapping("/{componentId}")
    public String getComponentById(@PathVariable Integer componentId, Model model) {
        model.addAttribute("component", componentInputPort.getById(componentId));
        return "ComponentsPage";    }

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
