package codigorapido.coshopcore.service;

import codigorapido.coshopcore.entity.GroupEntity;
import codigorapido.coshopcore.model.GroupCreate;
import codigorapido.coshopcore.repository.GroupRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;

    public GroupEntity createGroup(GroupCreate groupCreate) {
        GroupEntity groupEntity = GroupEntity.builder()
            .name(groupCreate.getName())
            .startDate(groupCreate.getStartDate())
            .endDate(groupCreate.getEndDate())
            .build();
        return groupRepository.save(groupEntity);
    }

    public Optional<GroupEntity> getGroupById(Long groupId) {
        return groupRepository.findById(groupId);
    }
}
