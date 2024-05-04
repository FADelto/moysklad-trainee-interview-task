package com.romashkako.myproducts.service;

import com.romashkako.myproducts.database.dto.ProductDTO;
import com.romashkako.myproducts.database.entity.Product;
import com.romashkako.myproducts.database.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;


    private void checkRestrictions(ProductDTO product) {
        if(!product.getName().isEmpty() && product.getName().length() < 256){
            if(product.getDescription().length()<4097){
                if (product.getPrice() < 0) {
                    throw new RuntimeException("Цена не может быть отрицательной");
                }
            }
            else throw new RuntimeException("Описание товара не соответствует требованиям");
        }
        else throw new RuntimeException("Название товара не соответствует требованиям");
    }

    public ProductDTO createProduct(ProductDTO product){
        checkRestrictions(product);
        productRepository.save(new Product(product.getName(), product.getDescription(), product.getPrice(), product.getInStock()));
        return product;
    }
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    public Product getProductById(Long id){
        return productRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Продукт с таким id не найден"));
    }
    public Product editProduct(ProductDTO product, Long id){
        checkRestrictions(product);
        Product oldProduct  = productRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Продукт с таким id не найден"));
        oldProduct.setName(product.getName());
        oldProduct.setDescription(product.getDescription());
        oldProduct.setPrice(product.getPrice());
        oldProduct.setInStock(product.getInStock());
        return productRepository.save(oldProduct);
    }

    public Product removeProduct(Long id){
        Product removedProduct  = productRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Продукт с таким id не найден"));
        productRepository.deleteById(id);
        return removedProduct;
    }

}
