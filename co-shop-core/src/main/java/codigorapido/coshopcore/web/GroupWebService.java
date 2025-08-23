package codigorapido.coshopcore.web;

import codigorapido.coshopcore.api.GroupsApi;
import codigorapido.coshopcore.model.Group;
import codigorapido.coshopcore.model.GroupCreate;
import codigorapido.coshopcore.model.JoinGroupRequest;
import codigorapido.coshopcore.model.Member;
import codigorapido.coshopcore.service.GroupService;
import codigorapido.coshopcore.service.SettlementService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GroupWebService implements GroupsApi {

    private final GroupService groupService;
    private final SettlementService settlementService;

    @Override
    public ResponseEntity<Group> createGroup(GroupCreate groupCreate) {
        var group = groupService.createGroup(groupCreate);
        return new ResponseEntity<>(group, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Group> getGroup(Long groupId) {
        var optionalGroup = groupService.getGroupById(groupId);
        if (optionalGroup.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            var group = optionalGroup.get();
            return new ResponseEntity<>(group, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<Void> joinGroup(JoinGroupRequest joinGroupRequest) {
        groupService.addMemberToGroup(joinGroupRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Member>> listGroupMembers(Long groupId) {
        var members = groupService.listGroupMembers(groupId);
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<String>> computeGroupSettlement(Long groupId) {
        var settlements = settlementService.computeGroupSettlement(groupId);
        return new ResponseEntity<>(settlements, HttpStatus.OK);
    }
}
