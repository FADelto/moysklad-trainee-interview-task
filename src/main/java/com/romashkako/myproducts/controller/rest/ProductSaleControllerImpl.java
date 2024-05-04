package com.romashkako.myproducts.controller.rest;

import com.romashkako.myproducts.database.dto.ErrorResponseDTO;
import com.romashkako.myproducts.database.dto.ProductSaleDTO;
import com.romashkako.myproducts.service.ProductSaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/productsales")
public class ProductSaleControllerImpl implements ProductSaleController{
    private final ProductSaleService productSaleService;
    @PostMapping("/create")
    public ResponseEntity<?> create(@Validated @RequestBody ProductSaleDTO productSale) {
        try {
            return ResponseEntity.ok().body(productSaleService.createProductSale(productSale));
        }
        catch (RuntimeException e){
            return ResponseEntity.status(412).body(new ErrorResponseDTO(e.getMessage()));
        }
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok().body(productSaleService.getAllProductSale());
    }

    @GetMapping("/getById")
    public ResponseEntity<?> getById(Long id){
        try {
            return ResponseEntity.ok().body(productSaleService.getProductSaleById(id));
        }
        catch(NoSuchElementException e){
            return ResponseEntity.status(404).body(new ErrorResponseDTO(e.getMessage()));
        }
    }

    @PatchMapping("/update")
    public ResponseEntity<?> update(@Validated @RequestBody ProductSaleDTO productSale, @RequestParam Long id) {
        try {
            return ResponseEntity.ok().body(productSaleService.editProductSale(productSale, id));
        }
        catch(NoSuchElementException e){
            return ResponseEntity.status(404).body(new ErrorResponseDTO(e.getMessage()));
        }
        catch (RuntimeException e){
            return ResponseEntity.status(412).body(new ErrorResponseDTO(e.getMessage()));
        }
    }

    @DeleteMapping("/remove")
    public ResponseEntity<?> remove(Long id) {
        try {
            return ResponseEntity.ok().body(productSaleService.removeProductSale(id));
        }
        catch(NoSuchElementException e){
            return ResponseEntity.status(404).body(new ErrorResponseDTO(e.getMessage()));
        }
        catch (RuntimeException e){
            return ResponseEntity.status(412).body(new ErrorResponseDTO(e.getMessage()));
        }
    }

}
