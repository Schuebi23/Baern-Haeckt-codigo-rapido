package codigorapido.coshopcore.web;

import codigorapido.coshopcore.api.ItemsApi;
import codigorapido.coshopcore.model.Item;
import codigorapido.coshopcore.model.ItemCreate;
import codigorapido.coshopcore.model.ItemUpdate;
import codigorapido.coshopcore.service.ItemService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ItemWebService implements ItemsApi {

    private final ItemService itemService;

    @Override
    public ResponseEntity<List<Item>> listGroupItems(Long groupId) {
        var items = itemService.listGroupItems(groupId);
        return ResponseEntity.ok(items);
    }

    @Override
    public ResponseEntity<Item> addGroupItem(Long groupId, ItemCreate itemCreate) {
        var item = itemService.addGroupItem(groupId, itemCreate);
        return ResponseEntity.ok(item);
    }

    @Override
    public ResponseEntity<Item> updateGroupItem(Long itemId, ItemUpdate itemUpdate) {
        var item = itemService.updateGroupItem(itemId, itemUpdate);
        return ResponseEntity.ok(item);
    }

    @Override
    public ResponseEntity<Void> removeGroupItem(Long itemId) {
        itemService.removeGroupItem(itemId);
        return ResponseEntity.noContent().build();
    }
}
