package HexagonalArchitectureDemoApplication.application.port.out;

import HexagonalArchitectureDemoApplication.application.domain.entity.Component;

import java.util.Collection;
import java.util.Optional;

public interface ComponentOutputPort {
    Collection<Component> getAllComponents();
    Optional<Component> findComponentById(Integer id);
    Component addComponent(Component component);
    Optional<Component> updateComponent(Integer id, Component component);
}
