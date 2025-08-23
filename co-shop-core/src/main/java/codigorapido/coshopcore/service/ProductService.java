package codigorapido.coshopcore.service;

import codigorapido.coshopcore.entity.ProductEntity;
import codigorapido.coshopcore.repository.ProductRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Optional<ProductEntity> findById(Long productId) {
        return productRepository.findById(productId);
    }

}
