package codigorapido.coshopcore.service;

import codigorapido.coshopcore.entity.GroupEntity;
import codigorapido.coshopcore.entity.converter.GroupCreateToEntityConverter;
import codigorapido.coshopcore.entity.converter.GroupEntityToDtoConverter;
import codigorapido.coshopcore.entity.converter.MemberEntityToDtoConverter;
import codigorapido.coshopcore.model.Group;
import codigorapido.coshopcore.model.GroupCreate;
import codigorapido.coshopcore.model.JoinGroupRequest;
import codigorapido.coshopcore.model.Member;
import codigorapido.coshopcore.repository.GroupRepository;
import codigorapido.coshopcore.repository.MemberRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final MemberRepository memberRepository;
    private final GroupCreateToEntityConverter toEntityConverter;

    private final GroupEntityToDtoConverter toDtoConverter = new GroupEntityToDtoConverter();

    private final MemberEntityToDtoConverter memberToDtoConverter = new MemberEntityToDtoConverter();

    public Group createGroup(GroupCreate groupCreate) {
        GroupEntity groupEntity = toEntityConverter.convert(groupCreate);
        var savedEntity = groupRepository.save(groupEntity);
        return toDtoConverter.convert(savedEntity);
    }

    public Optional<Group> getGroupById(Long groupId) {
        var entity = groupRepository.findById(groupId);
        return entity.map(toDtoConverter::convert);
    }

    public void addMemberToGroup(JoinGroupRequest joinGroupRequest) {
        var group = groupRepository.findByInviteCode(joinGroupRequest.getInviteCode()).orElseThrow();
        var member = memberRepository.findById(joinGroupRequest.getMemberId()).orElseThrow();

        group.getMembers().add(member);
        groupRepository.save(group);
    }

    public List<Member> listGroupMembers(Long groupId) {
        var group = groupRepository.findById(groupId).orElseThrow();
        return memberToDtoConverter.convert(group.getMembers());
    }
}
