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
}
