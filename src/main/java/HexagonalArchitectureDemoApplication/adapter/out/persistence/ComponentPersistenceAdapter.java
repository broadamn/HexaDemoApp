package HexagonalArchitectureDemoApplication.adapter.out.persistence;

import HexagonalArchitectureDemoApplication.adapter.out.persistence.jpaentity.ComponentEntity;
import HexagonalArchitectureDemoApplication.adapter.out.persistence.mapper.ComponentMapper;
import HexagonalArchitectureDemoApplication.adapter.out.persistence.repository.ComponentRepository;
import HexagonalArchitectureDemoApplication.application.domain.entity.Component;
import HexagonalArchitectureDemoApplication.application.port.out.ComponentOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ComponentPersistenceAdapter implements ComponentOutputPort {

    private final ComponentRepository componentRepository;

    private final ComponentMapper mapper;

    @Override
    public Collection<Component> getAllComponents() {
        return mapper.componentsFromComponentEntities(componentRepository.findAll());
    }

    @Override
    public Optional<Component> findComponentById(Integer id) {
        return componentRepository.findById(id).map(mapper::componentEntityToComponent);
    }

    @Override
    public Component addComponent(Component newComponent) {
        if (newComponent == null) {
            throw new IllegalArgumentException("New component cannot be null");
        }
        ComponentEntity componentEntity = mapper.componenttoComponentEntity(newComponent);
        componentEntity = componentRepository.save(componentEntity);
        return mapper.componentEntityToComponent(componentEntity);
    }

    public Optional<Component> updateComponent(Integer id, Component updatedComponent) {
        if (updatedComponent == null) {
            throw new IllegalArgumentException("Updated component cannot be null");
        }

        ComponentEntity existingComponentEntity = componentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Component not found with id: " + id));

        existingComponentEntity.setName(updatedComponent.getName());
        existingComponentEntity.setDescription(updatedComponent.getDescription());
        existingComponentEntity.setPrice(updatedComponent.getPrice());
        existingComponentEntity.setStock(updatedComponent.getStock());
        existingComponentEntity.setCategory(updatedComponent.getCategory());

        try {
            existingComponentEntity = componentRepository.save(existingComponentEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error updating component with id: " + id, e);
        }

        return Optional.ofNullable(mapper.componentEntityToComponent(existingComponentEntity));
    }
}

