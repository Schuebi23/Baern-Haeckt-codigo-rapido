package codigorapido.coshopcore.repository;

import codigorapido.coshopcore.entity.CommitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommitRepository extends JpaRepository<CommitEntity, Long> {

}
