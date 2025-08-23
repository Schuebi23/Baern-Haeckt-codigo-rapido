package codigorapido.coshopcore.entity.converter;

import codigorapido.coshopcore.common.converter.Converter;
import codigorapido.coshopcore.entity.RequestEntity;
import codigorapido.coshopcore.model.Request;

public class RequestEntityToDtoConverter implements Converter<RequestEntity, Request> {

    @Override
    public Request convert(RequestEntity in) {
        return new Request()
            .id(in.getId())
            .itemId(in.getItem().getId())
            .memberId(in.getMember().getId())
            .qtyRequested(in.getQuantity())
            .forEveryone(in.isForEveryone());
    }
}
