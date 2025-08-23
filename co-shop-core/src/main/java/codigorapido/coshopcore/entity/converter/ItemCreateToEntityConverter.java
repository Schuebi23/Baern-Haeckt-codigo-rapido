package codigorapido.coshopcore.entity.converter;

import codigorapido.coshopcore.common.converter.Converter;
import codigorapido.coshopcore.entity.ItemEntity;
import codigorapido.coshopcore.model.ItemCreate;
import codigorapido.coshopcore.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemCreateToEntityConverter implements Converter<ItemCreate, ItemEntity> {

    private final ProductRepository productRepository;

    @Override
    public ItemEntity convert(ItemCreate source) {
        var product = productRepository.findById(source.getProductId()).orElse(null);
        return ItemEntity.builder()
            .name(source.getName())
            .description(source.getDescription())
            .unit(source.getUnit())
            .product(product)
            .build();
    }
}
