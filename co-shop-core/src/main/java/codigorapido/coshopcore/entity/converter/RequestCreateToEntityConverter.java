package codigorapido.coshopcore.entity.converter;

import codigorapido.coshopcore.common.converter.Converter;
import codigorapido.coshopcore.entity.RequestEntity;
import codigorapido.coshopcore.model.RequestCreate;
import codigorapido.coshopcore.repository.ItemRepository;
import codigorapido.coshopcore.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RequestCreateToEntityConverter implements Converter<RequestCreate, RequestEntity> {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    @Override
    public RequestEntity convert(RequestCreate in) {
        var item = itemRepository.findById(in.getItemId())
            .orElseThrow(() -> new IllegalArgumentException("Item not found with id: " + in.getItemId()));
        var member = memberRepository.findById(in.getMemberId())
            .orElseThrow(() -> new IllegalArgumentException("Member not found with id: " + in.getMemberId()));
        return RequestEntity.builder()
            .item(item)
            .member(member)
            .quantity(in.getQtyRequested())
            .forEveryone(in.getForEveryone())
            .build();
    }
}
