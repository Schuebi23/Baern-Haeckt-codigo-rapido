package codigorapido.coshopcore.service;

import codigorapido.coshopcore.entity.CommitEntity;
import codigorapido.coshopcore.entity.ItemEntity;
import codigorapido.coshopcore.entity.converter.CommitCreateToEntityConverter;
import codigorapido.coshopcore.entity.converter.CommitEntityToDtoConverter;
import codigorapido.coshopcore.model.Commit;
import codigorapido.coshopcore.model.CommitCreate;
import codigorapido.coshopcore.model.CommitUpdate;
import codigorapido.coshopcore.repository.CommitRepository;
import codigorapido.coshopcore.repository.ItemRepository;
import codigorapido.coshopcore.repository.MemberRepository;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommitService {

    private final CommitRepository commitRepository;
    private final CommitCreateToEntityConverter toEntityConverter;

    private final CommitEntityToDtoConverter toDtoConverter = new CommitEntityToDtoConverter();

    public Commit addCommitToGroup(CommitCreate commitCreate) {
        var commitEntity = toEntityConverter.convert(commitCreate);
        var savedEntity = commitRepository.save(commitEntity);
        return toDtoConverter.convert(savedEntity);
    }

    public Commit updateCommit(Long commitId, CommitUpdate commitUpdate) {
        var commitEntity = commitRepository.findById(commitId)
                .orElseThrow(() -> new IllegalArgumentException("Commit not found with id: " + commitId));

        commitEntity.setQuantity(commitUpdate.getQtyCommitted());
        commitEntity.setPrice(BigDecimal.valueOf(commitUpdate.getPrice()));
        commitEntity.setCommitted(commitUpdate.getCommited());

        var savedEntity = commitRepository.save(commitEntity);

        return toDtoConverter.convert(savedEntity);
    }

    public void deleteCommit(Long commitId) {
        commitRepository.deleteById(commitId);
    }

    public List<Commit> findCommitsByItem(ItemEntity item) {
        var commitEntities = commitRepository.findAllByItem(item);
        return toDtoConverter.convert(commitEntities);
    }
}
