package uz.pdp.store.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.store.model.Discount;
import uz.pdp.store.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findAllByProductCategoryId(Integer id);

    List<Product> findAllByIdIn(List<Integer> list);

    List<Product> findAllByDiscount(Discount discount);

}
