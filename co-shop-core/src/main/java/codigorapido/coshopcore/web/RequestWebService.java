package codigorapido.coshopcore.web;

import codigorapido.coshopcore.api.RequestsApi;
import codigorapido.coshopcore.entity.RequestEntity;
import codigorapido.coshopcore.model.Request;
import codigorapido.coshopcore.model.RequestCreate;
import codigorapido.coshopcore.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RequestWebService implements RequestsApi {

    private final RequestService requestService;

    @Override
    public ResponseEntity<Request> createRequest(Long groupId, RequestCreate requestCreate) {
        var requestEntity = requestService.create(requestCreate);

        return ResponseEntity.ok(toDto(requestEntity));
    }

    @Override
    public ResponseEntity<Request> getRequest(Long requestId) {
        var requestEntity = requestService.getRequest(requestId);

        return ResponseEntity.ok(toDto(requestEntity));
    }

    @Override
    public ResponseEntity<Void> deleteRequest(Long requestId) {
        requestService.deleteRequest(requestId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Request> updateRequest(Long requestId, codigorapido.coshopcore.model.RequestUpdate requestUpdate) {
        var request = requestService.updateRequest(requestId, requestUpdate);
        return ResponseEntity.ok(toDto(request));
    }

    private Request toDto(RequestEntity entity) {
        return new Request()
                .id(entity.getId())
                .itemId(entity.getItem().getId())
                .qtyRequested(entity.getQuantity())
                .memberId(entity.getMember().getId());
    }
}
