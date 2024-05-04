package com.romashkako.myproducts.service;

import com.romashkako.myproducts.database.dto.ProductSaleDTO;
import com.romashkako.myproducts.database.entity.Product;
import com.romashkako.myproducts.database.entity.ProductSale;
import com.romashkako.myproducts.database.repository.ProductSaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class ProductSaleService {
    private final ProductSaleRepository productSaleRepository;
    private final ProductService productService;

    private void checkRestrictions(ProductSaleDTO productSupply) {
        if (!productSupply.getDocumentName().isEmpty() && productSupply.getDocumentName().length() < 256) {
            productService.getProductById(productSupply.getProductId());
            if (productSupply.getQuantityOfSoldProducts() < 1) {
                throw new RuntimeException("Количество проданных товаров не может быть отрицательным или равняться нулю");
            }
        } else throw new RuntimeException("Название документа не соответствует требованиям");
    }

    @Transactional
    public ProductSale createProductSale(ProductSaleDTO productSupply) {
        checkRestrictions(productSupply);
        Product product = productService.changeInStock(productSupply.getProductId(), -productSupply.getQuantityOfSoldProducts());
        return productSaleRepository.save(new ProductSale(productSupply.getDocumentName(),
                product,
                productSupply.getQuantityOfSoldProducts(),
                productSupply.getQuantityOfSoldProducts() * product.getPrice()));
    }

    public List<ProductSale> getAllProductSale() {
        return productSaleRepository.findAll();
    }

    public ProductSale getProductSaleById(Long id) {
        return productSaleRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Продажа товара с таким id не найдена"));
    }

    @Transactional
    public ProductSale editProductSale(ProductSaleDTO productSale, Long id) {
        checkRestrictions(productSale);
        ProductSale oldProductSale = productSaleRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Продажа товара с таким id не найдена"));
        Product product;
        if (!Objects.equals(productSale.getProductId(), oldProductSale.getProduct().getId())) {
            productService.changeInStock(oldProductSale.getProduct().getId(), oldProductSale.getQuantityOfSoldProducts());
            product = productService.changeInStock(productSale.getProductId(), -productSale.getQuantityOfSoldProducts());
        } else {
            if (productSale.getQuantityOfSoldProducts() < oldProductSale.getQuantityOfSoldProducts()) {
                product = productService.changeInStock(productSale.getProductId(), productSale.getQuantityOfSoldProducts());
            } else {
                product = productService.changeInStock(productSale.getProductId(), -productSale.getQuantityOfSoldProducts());
            }
        }
        oldProductSale.setDocumentName(productSale.getDocumentName());
        oldProductSale.setProduct(product);
        oldProductSale.setQuantityOfSoldProducts(productSale.getQuantityOfSoldProducts());
        oldProductSale.setCost(productSale.getQuantityOfSoldProducts()*product.getPrice());
        return productSaleRepository.save(oldProductSale);
    }

    @Transactional
    public ProductSale removeProductSale(Long id) {
        ProductSale removedProductSale = productSaleRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Продажа товара с таким id не найдена"));
        productSaleRepository.deleteById(id);
        productService.changeInStock(removedProductSale.getProduct().getId(), removedProductSale.getQuantityOfSoldProducts());
        return removedProductSale;
    }
}
