package codigorapido.coshopcore.web;

import codigorapido.coshopcore.api.CommitsApi;
import codigorapido.coshopcore.model.Commit;
import codigorapido.coshopcore.model.CommitCreate;
import codigorapido.coshopcore.model.CommitUpdate;
import codigorapido.coshopcore.service.CommitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommitWebService implements CommitsApi {

    private final CommitService commitService;

    @Override
    public ResponseEntity<Commit> createCommit(CommitCreate commitCreate) {
        var commit = commitService.addCommitToGroup(commitCreate);
        return ResponseEntity.ok(commit);
    }

    @Override
    public ResponseEntity<Commit> updateCommit(Long commitId, CommitUpdate commitUpdate) {
        var commit = commitService.updateCommit(commitId, commitUpdate);
        return ResponseEntity.ok(commit);
    }

    @Override
    public ResponseEntity<Void> deleteCommit(Long commitId) {
        commitService.deleteCommit(commitId);
        return ResponseEntity.noContent().build();
    }
}
