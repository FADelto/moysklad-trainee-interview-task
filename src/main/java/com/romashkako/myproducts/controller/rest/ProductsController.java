package com.romashkako.myproducts.controller.rest;

import com.romashkako.myproducts.database.dto.ErrorResponseDTO;
import com.romashkako.myproducts.database.dto.ProductDTO;
import com.romashkako.myproducts.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductsController implements ProductsInterface {
    private final ProductService productService;


    @PostMapping("/create")
    public ResponseEntity<?> create(@Validated @RequestBody ProductDTO product) {
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
    public ResponseEntity<?> getById(Long id){
        try {
            return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
        }
        catch(NoSuchElementException e){
            return ResponseEntity.status(404).body(new ErrorResponseDTO(e.getMessage()));
        }

    }


    @PatchMapping("/update")
    public ResponseEntity<?> update(@Validated @RequestBody ProductDTO product, @RequestParam Long id) {
        try {
            productService.editProduct(product, id);
        }
        catch(NoSuchElementException e){
            return ResponseEntity.status(404).body(new ErrorResponseDTO(e.getMessage()));
        }
        catch (RuntimeException e){
            return ResponseEntity.status(412).body(new ErrorResponseDTO(e.getMessage()));
        }
        return ResponseEntity.ok().body(product);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<?> remove(Long id) {
        try {
            return new ResponseEntity<>(productService.removeProduct(id), HttpStatus.OK);
        }
        catch(NoSuchElementException e){
            return ResponseEntity.status(404).body(new ErrorResponseDTO(e.getMessage()));
        }
    }
}
