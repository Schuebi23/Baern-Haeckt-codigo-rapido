package codigorapido.coshopcore.repository;

import codigorapido.coshopcore.entity.ItemEntity;
import codigorapido.coshopcore.entity.RequestEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<RequestEntity, Long> {

    List<RequestEntity> findAllByItem(ItemEntity item);
}
