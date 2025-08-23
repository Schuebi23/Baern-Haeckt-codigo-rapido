package codigorapido.coshopcore.service;

import codigorapido.coshopcore.entity.converter.ProductCreateToEntityConverter;
import codigorapido.coshopcore.entity.converter.ProductEntityToDtoConverter;
import codigorapido.coshopcore.model.Product;
import codigorapido.coshopcore.model.ProductCreate;
import codigorapido.coshopcore.model.ProductUpdate;
import codigorapido.coshopcore.repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductEntityToDtoConverter entityToDtoConverter;
    private final ProductCreateToEntityConverter domainToEntityConverter;

    public Optional<Product> findById(Long productId) {
        return Optional.ofNullable(entityToDtoConverter.convert(productRepository.findById(productId).orElseThrow()));
    }

    public Optional<List<Product>> findByName(String name) {
        return Optional.of(productRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(entityToDtoConverter::convert)
                .toList());
    }

    public void deleteById(Long productId) {
        productRepository.deleteById(productId);
    }

    public Product save(ProductCreate productCreate) {
        var productEntity = domainToEntityConverter.convert(productCreate);
        var savedEntity = productRepository.save(productEntity);
        return entityToDtoConverter.convert(savedEntity);
    }

    public Product update(ProductUpdate productUpdate) {
        var productEntity = productRepository.findById(productUpdate.getId()).orElseThrow();
        productEntity.setName(productUpdate.getName());
        productEntity.setUnit(productUpdate.getUnit());
        var updatedEntity = productRepository.save(productEntity);
        return entityToDtoConverter.convert(updatedEntity);
    }
}
