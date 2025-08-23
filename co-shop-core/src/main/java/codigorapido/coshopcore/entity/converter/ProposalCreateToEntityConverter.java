package codigorapido.coshopcore.entity.converter;

import codigorapido.coshopcore.common.converter.Converter;
import codigorapido.coshopcore.entity.ProposalEntity;
import codigorapido.coshopcore.model.ProposalCreate;
import codigorapido.coshopcore.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProposalCreateToEntityConverter implements Converter<ProposalCreate, ProposalEntity> {

    private final ItemRepository itemRepository;

    @Override
    public ProposalEntity convert(ProposalCreate in) {
        var item = itemRepository.findById(in.getItemId())
            .orElseThrow(() -> new IllegalArgumentException("Item not found with id: " + in.getItemId()));
        return ProposalEntity.builder()
            .item(item)
            .perPerson(in.getPerPerson())
            .perDay(in.getPerDay())
            .quantity(in.getBaseQty())
            .build();
    }

}
