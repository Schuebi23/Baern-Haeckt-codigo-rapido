package codigorapido.coshopcore.service;

import codigorapido.coshopcore.entity.ItemEntity;
import codigorapido.coshopcore.model.Item;
import codigorapido.coshopcore.model.ItemCreate;
import codigorapido.coshopcore.model.ItemUpdate;
import codigorapido.coshopcore.repository.ItemRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final GroupService groupService;
    private final CommitService commitService;
    private final ProposalService proposalService;
    private final ProductService productService;

    public List<Item> listGroupItems(Long groupId) {
        var group = groupService.getGroupById(groupId).orElseThrow();

        var items = itemRepository.findByGroup(group);

        return items.stream().map(itemEntity -> {
            var commits = commitService.findCommitsByItem(itemEntity);
            //var requests = requestService.findRequestsByItem(itemEntity);
            var proposals = proposalService.findProposalsByItem(itemEntity);

            return new Item()
                .id(itemEntity.getId())
                .name(itemEntity.getName() == null ? itemEntity.getProduct().getName() : itemEntity.getName())
                .description(itemEntity.getDescription())
                .groupId(groupId)
                .unit(itemEntity.getUnit())
                .commits(commits)
                .proposals(proposals);
            // TODO: get requests for the item
            //.requests(requests.size())
        }).toList();
    }

    public Item addGroupItem(Long groupId, ItemCreate itemCreate) {
        var group = groupService.getGroupById(groupId).orElseThrow();
        var product = productService.findById(itemCreate.getProductId()).orElse(null);

        var itemEntity = ItemEntity.builder()
            .group(group)
            .product(product)
            .description(itemCreate.getDescription())
            .name(itemCreate.getName())
            .unit(itemCreate.getUnit())
            .build();

        var savedItem = itemRepository.save(itemEntity);

        return new Item()
            .id(savedItem.getId())
            .name(savedItem.getName() == null ? savedItem.getProduct().getName() : savedItem.getName())
            .description(savedItem.getDescription())
            .groupId(groupId)
            .unit(savedItem.getUnit());
    }

    public Item updateGroupItem(Long itemId, ItemUpdate itemUpdate) {
        var savedItem = itemRepository.findById(itemId).orElseThrow();

        savedItem.setName(itemUpdate.getName());
        savedItem.setDescription(itemUpdate.getDescription());
        savedItem.setUnit(itemUpdate.getUnit());

        var updatedItem = itemRepository.save(savedItem);

        return new Item()
            .id(updatedItem.getId())
            .name(updatedItem.getName() == null ? updatedItem.getProduct().getName() : updatedItem.getName())
            .description(updatedItem.getDescription())
            .commits(commitService.findCommitsByItem(updatedItem))
            .unit(updatedItem.getUnit());
    }

    public void removeGroupItem(Long itemId) {
        itemRepository.deleteById(itemId);
    }
}
