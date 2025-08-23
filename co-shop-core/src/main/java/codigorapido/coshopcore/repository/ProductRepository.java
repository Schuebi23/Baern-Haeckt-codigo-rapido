package codigorapido.coshopcore.repository;

import codigorapido.coshopcore.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    // Falls du eine Teilübereinstimmung willst (LIKE %name%)
    List<ProductEntity> findByNameContainingIgnoreCase(String name);
}
