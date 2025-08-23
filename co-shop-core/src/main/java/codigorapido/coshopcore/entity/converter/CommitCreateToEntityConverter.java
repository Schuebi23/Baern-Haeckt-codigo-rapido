package codigorapido.coshopcore.entity.converter;

import codigorapido.coshopcore.common.converter.Converter;
import codigorapido.coshopcore.entity.CommitEntity;
import codigorapido.coshopcore.model.CommitCreate;
import codigorapido.coshopcore.repository.ItemRepository;
import codigorapido.coshopcore.repository.MemberRepository;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommitCreateToEntityConverter implements Converter<CommitCreate, CommitEntity> {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    @Override
    public CommitEntity convert(CommitCreate in) {
        var item = itemRepository.findById(in.getItemId())
            .orElseThrow(() -> new IllegalArgumentException("Item not found with id: " + in.getItemId()));
        var member = memberRepository.findById(in.getMemberId())
            .orElseThrow(() -> new IllegalArgumentException("Member not found with id: " + in.getMemberId()));

        return CommitEntity.builder()
            .item(item)
            .member(member)
            .quantity(in.getQtyCommitted())
            .price(BigDecimal.valueOf(in.getPrice()))
            .committed(in.getCommited())
            .build();
    }
}
