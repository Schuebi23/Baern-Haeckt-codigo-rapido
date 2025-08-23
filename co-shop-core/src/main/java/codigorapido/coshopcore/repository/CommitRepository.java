package codigorapido.coshopcore.repository;

import codigorapido.coshopcore.entity.CommitEntity;
import codigorapido.coshopcore.entity.ItemEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommitRepository extends JpaRepository<CommitEntity, Long> {

    List<CommitEntity> findAllByItem(ItemEntity item);
}
