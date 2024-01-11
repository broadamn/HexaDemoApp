package HexagonalArchitectureDemoApplication.application.domain.service;

import HexagonalArchitectureDemoApplication.adapter.in.web.dto.ComponentInDTO;
import HexagonalArchitectureDemoApplication.adapter.in.web.dto.ComponentOutDTO;
import HexagonalArchitectureDemoApplication.adapter.out.persistence.mapper.ComponentMapper;
import HexagonalArchitectureDemoApplication.application.domain.entity.Component;
import HexagonalArchitectureDemoApplication.application.domain.exception.InsuficientStock;
import HexagonalArchitectureDemoApplication.application.port.in.ComponentInputPort;
import HexagonalArchitectureDemoApplication.application.port.out.ComponentOutputPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@AllArgsConstructor
@Service
public class ComponentService implements ComponentInputPort {

    private final ComponentOutputPort componentOutputPort;

    private final ComponentMapper mapper;

    @Override
    public Collection<ComponentOutDTO> getAllComponents() {
        return mapper.dtosFromComponents(componentOutputPort.getAllComponents());
    }

    @Override
    public boolean purchaseComponent(Integer componentId , Integer amount) {
        Component component = componentOutputPort.findComponentById(componentId)
                .orElseThrow(() -> new RuntimeException("Component source not found"));
        try {
            component.decreaseStock(amount);
            componentOutputPort.updateComponent(componentId, component);
            return Boolean.TRUE;
        } catch (InsuficientStock e){
            log.info(e.getMessage());
            return Boolean.FALSE;
        }
    }

    @Override
    public void uploadComponent(ComponentInDTO componentInDTO) {
        Component component = mapper.componentFromDTO(componentInDTO);
        componentOutputPort.addComponent(component);
    }

    @Override
    public ComponentOutDTO getById(Integer id) {
        Component component = componentOutputPort.findComponentById(id)
                .orElseThrow(() -> new RuntimeException("Component source not found"));
        return mapper.dtoFromComponent(component);
    }
}
