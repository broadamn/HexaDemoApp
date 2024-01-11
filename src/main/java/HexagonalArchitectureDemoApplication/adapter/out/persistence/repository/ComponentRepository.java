package HexagonalArchitectureDemoApplication.adapter.out.persistence.repository;

import HexagonalArchitectureDemoApplication.adapter.out.persistence.jpaentity.ComponentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComponentRepository extends JpaRepository<ComponentEntity, Integer> {

}
