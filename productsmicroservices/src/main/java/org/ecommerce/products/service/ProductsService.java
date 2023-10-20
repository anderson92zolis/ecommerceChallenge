package org.ecommerce.products.service;

import lombok.AllArgsConstructor;
import org.ecommerce.products.entity.Products;
import org.ecommerce.products.repository.ProductsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductsService {

    private final ProductsRepository productsRepository;

    public List<Products> getAllProducts(){
        return productsRepository.findAll();
    }


}
