package codigorapido.coshopcore.service;

import codigorapido.coshopcore.entity.GroupEntity;
import codigorapido.coshopcore.entity.converter.GroupEntityToDtoConverter;
import codigorapido.coshopcore.model.Group;
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

    private final GroupEntityToDtoConverter groupEntityToDtoConverter = new GroupEntityToDtoConverter();

    public List<Group> getGroupsForMember(Long memberId) {
        var member = memberRepository.findById(memberId).orElseThrow();
        var groups = groupRepository.findByMembers(member);
        return groupEntityToDtoConverter.convert(groups);
    }

}
