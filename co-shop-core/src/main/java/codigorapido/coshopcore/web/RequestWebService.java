package codigorapido.coshopcore.web;

import codigorapido.coshopcore.api.RequestsApi;
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
    public ResponseEntity<Request> createRequest(RequestCreate requestCreate) {
        var request = requestService.create(requestCreate);
        return ResponseEntity.ok(request);
    }

    @Override
    public ResponseEntity<Request> updateRequest(Long requestId, codigorapido.coshopcore.model.RequestUpdate requestUpdate) {
        var request = requestService.updateRequest(requestId, requestUpdate);
        return ResponseEntity.ok(request);
    }

    @Override
    public ResponseEntity<Void> deleteRequest(Long requestId) {
        requestService.deleteRequest(requestId);
        return ResponseEntity.noContent().build();
    }
}
