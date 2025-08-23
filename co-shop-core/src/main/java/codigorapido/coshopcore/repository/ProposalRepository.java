package codigorapido.coshopcore.repository;

import codigorapido.coshopcore.entity.ProposalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProposalRepository extends JpaRepository<ProposalEntity, Long> {

}
