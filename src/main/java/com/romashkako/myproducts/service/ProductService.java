package com.romashkako.myproducts.service;

import com.romashkako.myproducts.database.dto.ProductDTO;
import com.romashkako.myproducts.database.entity.Product;
import com.romashkako.myproducts.database.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final EntityManager entityManager;

    private void checkRestrictions(ProductDTO product) {
        if (!product.getName().isEmpty() && product.getName().length() < 256) {
            if (product.getDescription().length() < 4097) {
                if (product.getPrice() < 0) {
                    throw new RuntimeException("Цена не может быть отрицательной");
                }
            } else throw new RuntimeException("Описание товара не соответствует требованиям");
        } else throw new RuntimeException("Название товара не соответствует требованиям");
    }

    public ProductDTO createProduct(ProductDTO product) {
        checkRestrictions(product);
        productRepository.save(new Product(product.getName(), product.getDescription(), product.getPrice(), product.getInStock()));
        return product;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Продукт с таким id не найден"));
    }

    public Product editProduct(ProductDTO product, Long id) {
        checkRestrictions(product);
        Product oldProduct = productRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Продукт с таким id не найден"));
        oldProduct.setName(product.getName());
        oldProduct.setDescription(product.getDescription());
        oldProduct.setPrice(product.getPrice());
        oldProduct.setInStock(product.getInStock());
        return productRepository.save(oldProduct);
    }

    public Product removeProduct(Long id) {
        Product removedProduct = productRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Продукт с таким id не найден"));
        productRepository.deleteById(id);
        return removedProduct;
    }

    public List<Product> findProducts(String name, Double minPrice, Double maxPrice, Boolean inStock, String orderSelect, Boolean orderDirectionAsc, Integer limit) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> product = cq.from(Product.class);
        List<Predicate> predicateList = new ArrayList<>();
        if (name != null) {
            predicateList.add(cb.like(cb.lower(product.get("name")), "%" + name.toLowerCase() + "%"));
        }
        if (minPrice != null) {
            predicateList.add(cb.greaterThanOrEqualTo(product.get("price"), minPrice));
        }
        if (maxPrice != null) {
            predicateList.add(cb.lessThanOrEqualTo(product.get("price"), maxPrice));
        }
        if (inStock !=null){
            predicateList.add(cb.equal(product.get("inStock"), inStock));
        }

        cq.select(product).where(predicateList.toArray(new Predicate[0]));
        if(orderSelect!=null && orderDirectionAsc!=null) {
            if (orderDirectionAsc) {
                cq.orderBy(cb.asc(product.get(orderSelect)));
            } else {
                cq.orderBy(cb.desc(product.get(orderSelect)));
            }
        }
        TypedQuery<Product> query = entityManager.createQuery(cq);
        if(limit!=null) query.setMaxResults(limit);
        return query.getResultList();
    }
}
