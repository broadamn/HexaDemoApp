package HexagonalArchitectureDemoApplication.adapter.out.persistence.mapper;

import HexagonalArchitectureDemoApplication.adapter.in.web.dto.ComponentInDTO;
import HexagonalArchitectureDemoApplication.adapter.in.web.dto.ComponentOutDTO;
import HexagonalArchitectureDemoApplication.adapter.out.persistence.jpaentity.ComponentEntity;
import HexagonalArchitectureDemoApplication.application.domain.entity.Component;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;

@Mapper(componentModel = "spring")
public abstract class ComponentMapper {

    @Mapping(target = "id", ignore = true)
    public abstract ComponentEntity componenttoComponentEntity(Component component);

    public abstract Component componentEntityToComponent(ComponentEntity componentEntity);

    @IterableMapping(elementTargetType = Component.class)
    public abstract Collection<Component> componentsFromComponentEntities(Collection<ComponentEntity> componentsEntities);

    @Mapping(target = "id", ignore = true)
    public abstract Component componentFromDTO(ComponentInDTO componentInDTO);

    public abstract ComponentOutDTO dtoFromComponent(Component component);

    @IterableMapping(elementTargetType = ComponentOutDTO.class)
    public abstract Collection<ComponentOutDTO> dtosFromComponents(Collection<Component> components);
}
