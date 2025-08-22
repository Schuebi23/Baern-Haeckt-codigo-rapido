package codigorapido.coshopcore.service;

import codigorapido.coshopcore.entity.GroupEntity;
import codigorapido.coshopcore.repository.GroupRepository;
import codigorapido.coshopcore.repository.MemberRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final GroupRepository groupRepository;

    public List<GroupEntity> getGroupsForMember(Long memberId) {
        var member = memberRepository.findById(memberId).orElseThrow();

        return groupRepository.findByMembers(member);
    }

}
