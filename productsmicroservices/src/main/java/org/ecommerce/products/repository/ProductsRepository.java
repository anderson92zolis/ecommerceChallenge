package org.ecommerce.products.repository;

import org.ecommerce.products.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends JpaRepository<Product,Integer> {

    Product getBySku(String sku);

    //Product findProduct(String ksu);
}
