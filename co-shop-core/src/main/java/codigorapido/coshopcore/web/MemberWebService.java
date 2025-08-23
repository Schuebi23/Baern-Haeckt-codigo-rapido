package codigorapido.coshopcore.web;

import codigorapido.coshopcore.api.MembersApi;
import codigorapido.coshopcore.model.Group;
import codigorapido.coshopcore.model.Member;
import codigorapido.coshopcore.service.MemberService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberWebService implements MembersApi {

    private final MemberService memberService;

    @Override
    public ResponseEntity<List<Group>> listMemberGroups(Long memberId) {
        var groupEntities = memberService.getGroupsForMember(memberId);

        var groups = groupEntities.stream()
            .map(ge -> new Group()
                .id(ge.getId())
                .name(ge.getName())
                .startDate(ge.getStartDate())
                .endDate(ge.getEndDate())
                .inviteCode("INVITE123")) // Placeholder for invite code logic
            .toList();

        return ResponseEntity.ok(groups);
    }

    @Override
    public ResponseEntity<Member> getMemberDetail(Long memberId) {
        var member = memberService.getMemberById(memberId);
        return ResponseEntity.ok(member);
    }
}
