package com.romashkako.myproducts.controller.rest;

import com.romashkako.myproducts.database.dto.ErrorResponseDTO;
import com.romashkako.myproducts.database.entity.Product;
import com.romashkako.myproducts.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductsController implements ProductsInterface {
    private final ProductService productService;


    @PostMapping("/create")
    public ResponseEntity<?> create(@Validated @RequestBody Product product) {
        try {
            return new ResponseEntity<>(productService.createProduct(product), HttpStatus.OK);
        }
        catch (RuntimeException e){
            return ResponseEntity.status(412).body(new ErrorResponseDTO(e.getMessage()));
        }
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }


    @GetMapping("/getById")
    public ResponseEntity<?> getById(int id){
        try {
            return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
        }
        catch(IndexOutOfBoundsException e){
            return ResponseEntity.status(404).body(new ErrorResponseDTO("Неверный id продукта"));
        }

    }

    @GetMapping("/getByName")
    public ResponseEntity<?> getByName(String name) {
        return new ResponseEntity<>(productService.getProductByName(name), HttpStatus.OK);
    }


    @PatchMapping("/update")
    public ResponseEntity<?> update(@Validated @RequestBody Product product, @RequestParam int id) {
        try {
            return ResponseEntity.ok().body(productService.editProduct(product, id));
        }
        catch(IndexOutOfBoundsException e){
            return ResponseEntity.status(404).body(new ErrorResponseDTO("Неверный id продукта"));
        }
        catch (RuntimeException e){
            return ResponseEntity.status(412).body(new ErrorResponseDTO(e.getMessage()));
        }
    }

    @DeleteMapping("/remove")
    public ResponseEntity<?> remove(int id) {
        try {
            return new ResponseEntity<>(productService.removeProduct(id), HttpStatus.OK);
        }
        catch(IndexOutOfBoundsException e){
            return ResponseEntity.status(404).body(new ErrorResponseDTO("Неверный id продукта"));
        }
    }
}
