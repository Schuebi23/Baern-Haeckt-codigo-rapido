package codigorapido.coshopcore.service;

import codigorapido.coshopcore.entity.ItemEntity;
import codigorapido.coshopcore.entity.ProposalEntity;
import codigorapido.coshopcore.entity.converter.ProposalCreateToEntityConverter;
import codigorapido.coshopcore.entity.converter.ProposalEntityToDtoConverter;
import codigorapido.coshopcore.model.Proposal;
import codigorapido.coshopcore.model.ProposalCreate;
import codigorapido.coshopcore.model.ProposalUpdate;
import codigorapido.coshopcore.repository.ItemRepository;
import codigorapido.coshopcore.repository.ProposalRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProposalService {

    private final ProposalRepository proposalRepository;
    private final ProposalCreateToEntityConverter toEntityConverter;

    private final ProposalEntityToDtoConverter toDtoConverter = new ProposalEntityToDtoConverter();

    public Proposal createProposal(ProposalCreate proposalCreate) {
        var proposalEntity = toEntityConverter.convert(proposalCreate);
        var savedEntity = proposalRepository.save(proposalEntity);
        return toDtoConverter.convert(savedEntity);
    }

    public Proposal updateProposal(Long proposalId, ProposalUpdate proposalUpdate) {
        var proposalEntity = proposalRepository.findById(proposalId)
            .orElseThrow(() -> new IllegalArgumentException("Proposal not found with id: " + proposalId));

        proposalEntity.setPerPerson(proposalUpdate.getPerPerson());
        proposalEntity.setPerDay(proposalUpdate.getPerDay());
        proposalEntity.setQuantity(proposalUpdate.getBaseQty());

        var savedEntity = proposalRepository.save(proposalEntity);

        return toDtoConverter.convert(savedEntity);
    }

    public List<Proposal> findProposalsByItem(ItemEntity itemEntity) {
        var proposals = proposalRepository.findAllByItem(itemEntity);
        return toDtoConverter.convert(proposals);
    }

    public void deleteProposal(Long proposalId) {
        proposalRepository.deleteById(proposalId);
    }


}
