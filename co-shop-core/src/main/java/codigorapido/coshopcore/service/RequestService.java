package codigorapido.coshopcore.service;

import codigorapido.coshopcore.entity.RequestEntity;
import codigorapido.coshopcore.model.RequestCreate;
import codigorapido.coshopcore.model.RequestUpdate;
import codigorapido.coshopcore.repository.ItemRepository;
import codigorapido.coshopcore.repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RequestService {

    private final RequestRepository requestRepository;
    private final ItemRepository itemRepository;

    public RequestEntity create(RequestCreate requestCreate) {
        var itemEntity = itemRepository.getReferenceById(requestCreate.getItemId());

        RequestEntity requestEntity = RequestEntity.builder()
                .item(itemEntity)
                .quantity(requestCreate.getQtyRequested())
                .forEveryone(requestCreate.getForEveryone())
                .build();

        return requestRepository.save(requestEntity);
    }

    public RequestEntity getRequest(Long requestId) {
        return requestRepository.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("Request not found with id: " + requestId));
    }

    public void deleteRequest(Long requestId) {
        requestRepository.deleteById(requestId);
    }

    public RequestEntity updateRequest(Long requestId, RequestUpdate requestUpdate) {
        var requestEntity = requestRepository.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("Request not found with id: " + requestId));

        if (requestUpdate.getQtyRequested() != null) {
            requestEntity.setQuantity(requestUpdate.getQtyRequested());
        }

       return requestRepository.save(requestEntity);
    }
}
