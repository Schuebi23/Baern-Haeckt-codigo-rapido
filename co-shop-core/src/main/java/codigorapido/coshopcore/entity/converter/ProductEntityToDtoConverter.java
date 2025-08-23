package codigorapido.coshopcore.entity.converter;

import codigorapido.coshopcore.common.converter.Converter;
import codigorapido.coshopcore.entity.ProductEntity;
import codigorapido.coshopcore.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductEntityToDtoConverter implements Converter<ProductEntity, Product> {

    @Override
    public Product convert(ProductEntity productEntity) {
        return new Product()
            .id(productEntity.getId())
            .name(productEntity.getName())
            .unit(productEntity.getUnit());
    }
}
