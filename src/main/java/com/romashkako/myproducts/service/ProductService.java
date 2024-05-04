package com.romashkako.myproducts.service;

import com.romashkako.myproducts.database.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private List<Product> productList;

    public ProductService(List<Product> productList) {
        this.productList = productList;
    }

    private void checkRestrictions(Product product) {
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

    public Product createProduct(Product product){
        checkRestrictions(product);
        productList.add(product);
        return product;
    }
    public List<Product> getAllProducts(){
        return productList;
    }
    public List<Product> getProductByName(String name){
        return productList.stream().filter(product -> product.getName().equals(name)).collect(Collectors.toList());
    }
    public Product getProductById(int id){
        return productList.get(id);
    }
    public Product editProduct(Product product, int id){
        checkRestrictions(product);
        productList.set(id, product);
        return product;
    }

    public Product removeProduct(int id){
        return productList.remove(id);
    }

}
