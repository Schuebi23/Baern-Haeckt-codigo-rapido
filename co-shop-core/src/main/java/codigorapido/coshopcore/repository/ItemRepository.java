package codigorapido.coshopcore.repository;

import codigorapido.coshopcore.entity.GroupEntity;
import codigorapido.coshopcore.entity.ItemEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

    List<ItemEntity> findByGroup(GroupEntity group);
}
