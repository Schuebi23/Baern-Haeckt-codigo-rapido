package codigorapido.coshopcore.service;

import codigorapido.coshopcore.entity.CommitEntity;
import codigorapido.coshopcore.model.CommitCreate;
import codigorapido.coshopcore.model.CommitUpdate;
import codigorapido.coshopcore.repository.CommitRepository;
import codigorapido.coshopcore.repository.ItemRepository;
import codigorapido.coshopcore.repository.MemberRepository;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommitService {

    private final CommitRepository commitRepository;
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    public CommitEntity addCommitToGroup(CommitCreate commitCreate) {
        var item = itemRepository.findById(commitCreate.getItemId())
                .orElseThrow(() -> new IllegalArgumentException("Item not found with id: " + commitCreate.getItemId()));
        var member = memberRepository.findById(commitCreate.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("Member not found with id: " + commitCreate.getMemberId()));

        var commitEntity = new CommitEntity();
        commitEntity.setItem(item);
        commitEntity.setMember(member);
        commitEntity.setQuantity(commitCreate.getQtyCommitted());
        commitEntity.setPrice(BigDecimal.valueOf(commitCreate.getPrice()));
        commitEntity.setCommitted(commitCreate.getCommited());

        return commitRepository.save(commitEntity);

    }

    public CommitEntity updateCommit(Long commitId, CommitUpdate commitUpdate) {
        var commitEntity = commitRepository.findById(commitId)
                .orElseThrow(() -> new IllegalArgumentException("Commit not found with id: " + commitId));

        commitEntity.setQuantity(commitUpdate.getQtyCommitted());
        commitEntity.setPrice(BigDecimal.valueOf(commitUpdate.getPrice()));
        commitEntity.setCommitted(commitUpdate.getCommited());

        return commitRepository.save(commitEntity);
    }

    public void deleteCommit(Long commitId) {
        commitRepository.deleteById(commitId);
    }
}
