package com.romashkako.myproducts.service;


import com.romashkako.myproducts.database.dto.ProductSupplyDTO;
import com.romashkako.myproducts.database.entity.Product;
import com.romashkako.myproducts.database.entity.ProductSupply;
import com.romashkako.myproducts.database.repository.ProductSupplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductSupplyService {
    private final ProductSupplyRepository productSupplyRepository;
    private final ProductService productService;

    private void checkRestrictions(ProductSupplyDTO productSupply) {
        if (!productSupply.getDocumentName().isEmpty() && productSupply.getDocumentName().length() < 256) {
            productService.getProductById(productSupply.getProductId());
            if (productSupply.getQuantityOfDeliveredProducts() < 1) {
                throw new RuntimeException("Количество поставленных товаров не может быть отрицательным или равняться нулю");
            }
        } else throw new RuntimeException("Название документа не соответствует требованиям");
    }

    @Transactional
    public ProductSupply createProductSupply(ProductSupplyDTO productSupply) {
        checkRestrictions(productSupply);
        Product product = productService.changeInStock(productSupply.getProductId(), productSupply.getQuantityOfDeliveredProducts());
        return productSupplyRepository.save(new ProductSupply(productSupply.getDocumentName(),
                product,
                productSupply.getQuantityOfDeliveredProducts()));
    }

    public List<ProductSupply> getAllProductSupply() {
        return productSupplyRepository.findAll();
    }

    public ProductSupply getProductSupplyById(Long id) {
        return productSupplyRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Поставка товара с таким id не найдена"));
    }

    @Transactional
    public ProductSupply editProductSupply(ProductSupplyDTO productSupply, Long id) {
        checkRestrictions(productSupply);
        ProductSupply oldProductSupply = productSupplyRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Поставка товара с таким id не найдена"));
        Product product;
        if (!Objects.equals(productSupply.getProductId(), oldProductSupply.getProduct().getId())) {
            productService.changeInStock(oldProductSupply.getProduct().getId(), -oldProductSupply.getQuantityOfDeliveredProducts());
            product = productService.changeInStock(productSupply.getProductId(), productSupply.getQuantityOfDeliveredProducts());
        } else {
            if (productSupply.getQuantityOfDeliveredProducts() < oldProductSupply.getQuantityOfDeliveredProducts()) {
                product = productService.changeInStock(productSupply.getProductId(), productSupply.getQuantityOfDeliveredProducts()-oldProductSupply.getQuantityOfDeliveredProducts());
            } else {
                product = productService.changeInStock(productSupply.getProductId(), -productSupply.getQuantityOfDeliveredProducts()+oldProductSupply.getQuantityOfDeliveredProducts());
            }
        }
        oldProductSupply.setDocumentName(productSupply.getDocumentName());
        oldProductSupply.setProduct(product);
        oldProductSupply.setQuantityOfDeliveredProducts(productSupply.getQuantityOfDeliveredProducts());
        return productSupplyRepository.save(oldProductSupply);
    }
    @Transactional
    public ProductSupply removeProductSupply(Long id) {
        ProductSupply removedProductSupply = productSupplyRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Поставка товара с таким id не найдена"));
        productSupplyRepository.deleteById(id);
        productService.changeInStock(removedProductSupply.getProduct().getId(), -removedProductSupply.getQuantityOfDeliveredProducts());
        return removedProductSupply;
    }
}
