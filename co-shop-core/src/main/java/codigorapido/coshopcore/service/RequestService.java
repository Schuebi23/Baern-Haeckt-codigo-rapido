package codigorapido.coshopcore.service;

import codigorapido.coshopcore.entity.ItemEntity;
import codigorapido.coshopcore.entity.RequestEntity;
import codigorapido.coshopcore.entity.converter.RequestCreateToEntityConverter;
import codigorapido.coshopcore.entity.converter.RequestEntityToDtoConverter;
import codigorapido.coshopcore.model.Request;
import codigorapido.coshopcore.model.RequestCreate;
import codigorapido.coshopcore.model.RequestUpdate;
import codigorapido.coshopcore.repository.ItemRepository;
import codigorapido.coshopcore.repository.RequestRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RequestService {

    private final RequestRepository requestRepository;
    private final RequestCreateToEntityConverter toEntityConverter;

    private final RequestEntityToDtoConverter toDtoConverter = new RequestEntityToDtoConverter();

    public Request create(RequestCreate requestCreate) {
        var requestEntity = toEntityConverter.convert(requestCreate);
        var savedEntity = requestRepository.save(requestEntity);
        return toDtoConverter.convert(savedEntity);
    }

    public Request updateRequest(Long requestId, RequestUpdate requestUpdate) {
        var requestEntity = requestRepository.findById(requestId)
            .orElseThrow(() -> new IllegalArgumentException("Request not found with id: " + requestId));

        requestEntity.setQuantity(requestUpdate.getQtyRequested());
        requestEntity.setForEveryone(requestUpdate.getForEveryone());

        var savedEntity = requestRepository.save(requestEntity);

        return toDtoConverter.convert(savedEntity);
    }

    public List<Request> findRequestsByItem(ItemEntity item) {
        var requests = requestRepository.findAllByItem(item);
        return toDtoConverter.convert(requests);
    }

    public void deleteRequest(Long requestId) {
        requestRepository.deleteById(requestId);
    }
}
