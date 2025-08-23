package codigorapido.coshopcore.service;

import codigorapido.coshopcore.entity.ItemEntity;
import codigorapido.coshopcore.entity.ProposalEntity;
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
    private final ItemRepository itemRepository;

    public Proposal createProposal(ProposalCreate proposalCreate) {
        var item = itemRepository.findById(proposalCreate.getItemId())
            .orElseThrow(() -> new IllegalArgumentException("Item not found with id: " + proposalCreate.getItemId()));

        var proposalEntity = ProposalEntity.builder()
            .item(item)
            .perPerson(proposalCreate.getPerPerson())
            .perDay(proposalCreate.getPerDay())
            .quantity(proposalCreate.getBaseQty())
            .build();

        var savedEntity = proposalRepository.save(proposalEntity);

        return new Proposal()
            .id(savedEntity.getId())
            .itemId(proposalEntity.getItem().getId())
            .perPerson(proposalEntity.isPerPerson())
            .perDay(proposalEntity.isPerDay())
            .baseQty(proposalEntity.getQuantity());
    }

    public Proposal updateProposal(Long proposalId, ProposalUpdate proposalUpdate) {
        var proposalEntity = proposalRepository.findById(proposalId)
            .orElseThrow(() -> new IllegalArgumentException("Proposal not found with id: " + proposalId));

        proposalEntity.setPerPerson(proposalUpdate.getPerPerson());
        proposalEntity.setPerDay(proposalUpdate.getPerDay());
        proposalEntity.setQuantity(proposalUpdate.getBaseQty());

        var savedEntity = proposalRepository.save(proposalEntity);

        return new Proposal()
            .id(savedEntity.getId())
            .itemId(proposalEntity.getItem().getId())
            .perPerson(proposalEntity.isPerPerson())
            .perDay(proposalEntity.isPerDay())
            .baseQty(proposalEntity.getQuantity());
    }

    public void deleteProposal(Long proposalId) {
        proposalRepository.deleteById(proposalId);
    }

    public List<Proposal> findProposalsByItem(ItemEntity itemEntity) {
        var proposals = proposalRepository.findAllByItem(itemEntity);

        return proposals.stream().map(proposalEntity -> new Proposal()
            .id(proposalEntity.getId())
            .itemId(proposalEntity.getItem().getId())
            .perPerson(proposalEntity.isPerPerson())
            .perDay(proposalEntity.isPerDay())
            .baseQty(proposalEntity.getQuantity())
        ).toList();
    }

}
