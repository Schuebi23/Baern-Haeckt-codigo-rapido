package codigorapido.coshopcore.entity.converter;

import codigorapido.coshopcore.common.converter.Converter;
import codigorapido.coshopcore.entity.CommitEntity;
import codigorapido.coshopcore.model.Commit;

public class CommitEntityToDtoConverter implements Converter<CommitEntity, Commit> {

    @Override
    public Commit convert(CommitEntity in) {
        return new Commit()
            .id(in.getId())
            .itemId(in.getItem().getId())
            .memberId(in.getMember().getId())
            .qtyCommitted(in.getQuantity())
            .price(in.getPrice().floatValue())
            .commited(in.isCommitted());
    }

}
