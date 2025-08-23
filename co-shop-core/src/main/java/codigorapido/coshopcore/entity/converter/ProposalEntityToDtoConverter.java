package codigorapido.coshopcore.entity.converter;

import codigorapido.coshopcore.common.converter.Converter;
import codigorapido.coshopcore.entity.ProposalEntity;
import codigorapido.coshopcore.model.Proposal;

public class ProposalEntityToDtoConverter implements Converter<ProposalEntity, Proposal> {

    @Override
    public Proposal convert(ProposalEntity in) {
        return new Proposal()
            .id(in.getId())
            .itemId(in.getItem().getId())
            .perPerson(in.isPerPerson())
            .perDay(in.isPerDay())
            .baseQty(in.getQuantity());
    }

}
