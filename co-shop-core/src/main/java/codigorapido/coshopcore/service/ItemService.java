package codigorapido.coshopcore.service;

import codigorapido.coshopcore.entity.converter.ItemCreateToEntityConverter;
import codigorapido.coshopcore.entity.converter.ItemEntityToDtoConverter;
import codigorapido.coshopcore.model.Item;
import codigorapido.coshopcore.model.ItemCreate;
import codigorapido.coshopcore.model.ItemUpdate;
import codigorapido.coshopcore.repository.GroupRepository;
import codigorapido.coshopcore.repository.ItemRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final GroupRepository groupRepository;

    private final ItemCreateToEntityConverter toEntityConverter;
    private final ItemEntityToDtoConverter toDtoConverter;

    public List<Item> listGroupItems(Long groupId) {
        var group = groupRepository.findById(groupId).orElseThrow();
        var items = itemRepository.findByGroup(group);
        return toDtoConverter.convert(items);
    }

    public Item addGroupItem(Long groupId, ItemCreate itemCreate) {
        var group = groupRepository.findById(groupId).orElseThrow();
        var itemEntity = toEntityConverter.convert(itemCreate);
        itemEntity.setGroup(group);
        var savedItem = itemRepository.save(itemEntity);

        return toDtoConverter.convert(savedItem);
    }

    public Item updateGroupItem(Long itemId, ItemUpdate itemUpdate) {
        var savedItem = itemRepository.findById(itemId).orElseThrow();
        savedItem.setName(itemUpdate.getName());
        savedItem.setDescription(itemUpdate.getDescription());
        savedItem.setUnit(itemUpdate.getUnit());
        var updatedItem = itemRepository.save(savedItem);

        return toDtoConverter.convert(updatedItem);
    }

    public void removeGroupItem(Long itemId) {
        itemRepository.deleteById(itemId);
    }
}
