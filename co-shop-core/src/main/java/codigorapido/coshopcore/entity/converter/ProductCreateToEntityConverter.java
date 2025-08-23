package codigorapido.coshopcore.entity.converter;

import codigorapido.coshopcore.common.converter.Converter;
import codigorapido.coshopcore.entity.ProductEntity;
import codigorapido.coshopcore.model.ProductCreate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductCreateToEntityConverter implements Converter<ProductCreate, ProductEntity> {

    @Override
    public ProductEntity convert(ProductCreate in) {
        return ProductEntity.builder()
            .name(in.getName())
            .unit(in.getUnit())
            .build();
    }
}
