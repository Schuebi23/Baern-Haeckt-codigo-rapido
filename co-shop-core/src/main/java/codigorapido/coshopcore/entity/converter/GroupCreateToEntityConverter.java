package codigorapido.coshopcore.entity.converter;

import codigorapido.coshopcore.common.converter.Converter;
import codigorapido.coshopcore.common.util.InviteCodeGenerator;
import codigorapido.coshopcore.entity.GroupEntity;
import codigorapido.coshopcore.model.GroupCreate;
import codigorapido.coshopcore.repository.MemberRepository;
import java.util.HashSet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupCreateToEntityConverter implements Converter<GroupCreate, GroupEntity> {

    private final MemberRepository memberRepository;

    @Override
    public GroupEntity convert(GroupCreate group) {
        var members = new HashSet<>(memberRepository.findAllById(group.getMembers()));

        return GroupEntity.builder()
            .name(group.getName())
            .startDate(group.getStartDate())
            .endDate(group.getEndDate())
            .members(members)
            .inviteCode(InviteCodeGenerator.generateCode())
            .build();
    }
}
