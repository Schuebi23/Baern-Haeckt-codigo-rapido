package codigorapido.coshopcore.entity.converter;

import codigorapido.coshopcore.common.converter.Converter;
import codigorapido.coshopcore.entity.ItemEntity;
import codigorapido.coshopcore.model.Item;
import codigorapido.coshopcore.service.CommitService;
import codigorapido.coshopcore.service.ProposalService;
import codigorapido.coshopcore.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemEntityToDtoConverter implements Converter<ItemEntity, Item> {

    private final CommitService commitService;
    private final RequestService requestService;
    private final ProposalService proposalService;

    @Override
    public Item convert(ItemEntity itemEntity) {
        var commits = commitService.findCommitsByItem(itemEntity);
        var requests = requestService.findRequestsByItem(itemEntity);
        var proposals = proposalService.findProposalsByItem(itemEntity);

        return new Item()
            .id(itemEntity.getId())
            .name(itemEntity.getName() == null ? itemEntity.getProduct().getName() : itemEntity.getName())
            .description(itemEntity.getDescription())
            .groupId(itemEntity.getGroup().getId())
            .unit(itemEntity.getUnit())
            .commits(commits)
            .proposals(proposals)
            .requests(requests);
    }
}
