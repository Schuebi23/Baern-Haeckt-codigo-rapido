package codigorapido.coshopcore.repository;

import codigorapido.coshopcore.entity.GroupEntity;
import codigorapido.coshopcore.entity.MemberEntity;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<GroupEntity, Long> {
    List<GroupEntity> findAllByMembers(Set<MemberEntity> members);

    Optional<GroupEntity> findByInviteCode(String inviteCode);
}
