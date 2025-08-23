package codigorapido.coshopcore.web;

import codigorapido.coshopcore.api.CommitsApi;
import codigorapido.coshopcore.model.Commit;
import codigorapido.coshopcore.model.CommitCreate;
import codigorapido.coshopcore.model.CommitUpdate;
import codigorapido.coshopcore.service.CommitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommitWebService implements CommitsApi {

    private final CommitService commitService;

    @Override
    public ResponseEntity<Commit> createCommit(CommitCreate commitCreate) {
        var commitEntity = commitService.addCommitToGroup(commitCreate);

        var commit = new Commit()
            .id(commitEntity.getId())
            .itemId(commitEntity.getItem().getId())
            .memberId(commitEntity.getMember().getId())
            .qtyCommitted(commitEntity.getQuantity());

        return ResponseEntity.ok(commit);
    }

    @Override
    public ResponseEntity<Commit> updateCommit(Long commitId, CommitUpdate commitUpdate) {
        var commitEntity = commitService.updateCommit(commitId, commitUpdate);

        var commit = new Commit()
            .id(commitEntity.getId())
            .itemId(commitEntity.getItem().getId())
            .memberId(commitEntity.getMember().getId())
            .qtyCommitted(commitEntity.getQuantity());

        return ResponseEntity.ok(commit);
    }

    @Override
    public ResponseEntity<Void> deleteCommit(Long commitId) {
        commitService.deleteCommit(commitId);
        return ResponseEntity.noContent().build();
    }
}
