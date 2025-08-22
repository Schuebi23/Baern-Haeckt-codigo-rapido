package codigorapido.coshopcore.web;


import codigorapido.coshopcore.api.GroupsApi;
import codigorapido.coshopcore.model.Group;
import codigorapido.coshopcore.model.GroupCreate;
import codigorapido.coshopcore.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GroupWebService implements GroupsApi {

    private final GroupService groupService;

    @Override
    public ResponseEntity<Group> createGroup(GroupCreate groupCreate) {
        var savedGroup = groupService.createGroup(groupCreate);

        var group = new Group()
            .id(savedGroup.getId())
            .name(savedGroup.getName())
            .startDate(savedGroup.getStartDate())
            .endDate(savedGroup.getEndDate())
            .inviteCode("INVITE123"); // Placeholder for invite code logic
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Group> getGroup(Long groupId) {
        var optionalGroup = groupService.getGroupById(groupId);
        if (optionalGroup.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            var groupEntity = optionalGroup.get();
            var group = new Group()
                .id(groupEntity.getId())
                .name(groupEntity.getName())
                .startDate(groupEntity.getStartDate())
                .endDate(groupEntity.getEndDate())
                .inviteCode("INVITE123"); // Placeholder for invite code logic
            return new ResponseEntity<>(group, HttpStatus.OK);
        }
    }
}
