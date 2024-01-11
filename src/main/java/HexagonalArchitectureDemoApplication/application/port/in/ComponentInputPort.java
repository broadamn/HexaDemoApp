package HexagonalArchitectureDemoApplication.application.port.in;

import HexagonalArchitectureDemoApplication.adapter.in.web.dto.ComponentInDTO;
import HexagonalArchitectureDemoApplication.adapter.in.web.dto.ComponentOutDTO;

import java.util.Collection;

public interface ComponentInputPort {
    Collection<ComponentOutDTO> getAllComponents();
    boolean purchaseComponent(Integer componentId, Integer amount);
    void uploadComponent(ComponentInDTO componentInDTO);

    ComponentOutDTO getById(Integer id);
}
