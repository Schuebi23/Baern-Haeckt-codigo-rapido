package codigorapido.coshopcore.web;

import codigorapido.coshopcore.api.ProductsApi;
import codigorapido.coshopcore.model.Product;
import codigorapido.coshopcore.model.ProductCreate;
import codigorapido.coshopcore.model.ProductUpdate;
import codigorapido.coshopcore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductWebService implements ProductsApi {

    private final ProductService productService;

    @Override
    public ResponseEntity<List<Product>> listProducts(String name) {
        return ResponseEntity.ok(productService.findByName(name).orElseThrow());
    }

    @Override
    public ResponseEntity<Void> deleteProduct(Long productId) {
        productService.deleteById(productId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Product> createProduct(ProductCreate productCreate) {
        return ResponseEntity.ok(productService.save(productCreate));
    }

    @Override
    public ResponseEntity<Product> updateProduct(Long productId, ProductUpdate productUpdate){
        return ResponseEntity.ok(productService.update(productUpdate));
    }
}
