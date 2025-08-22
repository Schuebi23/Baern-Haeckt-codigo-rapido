package codigorapido.coshopcore.web;


import codigorapido.coshopcore.api.GroupsApi;
import codigorapido.coshopcore.model.Group;
import codigorapido.coshopcore.model.GroupCreate;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GroupWebService implements GroupsApi {

    @Override
    public ResponseEntity<Group> createGroup(GroupCreate groupCreate) {
        Group saved = new Group()
            .id(UUID.randomUUID())
            .name(groupCreate.getName())
            .currency(groupCreate.getCurrency() != null ? groupCreate.getCurrency() : "CHF")
            .startDate(groupCreate.getStartDate())
            .endDate(groupCreate.getEndDate())
            .inviteCode(UUID.randomUUID().toString().substring(0, 6).toUpperCase())
            .createdAt(OffsetDateTime.now())
            .updatedAt(OffsetDateTime.now());

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

}
