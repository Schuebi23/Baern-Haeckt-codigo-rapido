package codigorapido.coshopcore.repository;

import codigorapido.coshopcore.entity.ItemEntity;
import codigorapido.coshopcore.entity.ProposalEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProposalRepository extends JpaRepository<ProposalEntity, Long> {

    List<ProposalEntity> findAllByItem(ItemEntity item);
}
