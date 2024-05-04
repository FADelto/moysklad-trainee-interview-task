package com.romashkako.myproducts.controller.rest;

import com.romashkako.myproducts.database.dto.ErrorResponseDTO;
import com.romashkako.myproducts.database.dto.ProductSupplyDTO;
import com.romashkako.myproducts.service.ProductSupplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/productsupply")
public class ProductSupplyControllerImpl implements ProductSupplyController{
    private final ProductSupplyService productSupplyService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@Validated @RequestBody ProductSupplyDTO productSupply) {
        try {
            return ResponseEntity.ok().body(productSupplyService.createProductSupply(productSupply));
        }
        catch (RuntimeException e){
            return ResponseEntity.status(412).body(new ErrorResponseDTO(e.getMessage()));
        }
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok().body(productSupplyService.getAllProductSupply());
    }

    @GetMapping("/getById")
    public ResponseEntity<?> getById(Long id){
        try {
            return ResponseEntity.ok().body(productSupplyService.getProductSupplyById(id));
        }
        catch(NoSuchElementException e){
            return ResponseEntity.status(404).body(new ErrorResponseDTO(e.getMessage()));
        }
        catch (RuntimeException e){
            return ResponseEntity.status(412).body(new ErrorResponseDTO(e.getMessage()));
        }
    }

    @PatchMapping("/update")
    public ResponseEntity<?> update(@Validated @RequestBody ProductSupplyDTO productSupply, @RequestParam Long id) {
        try {
            return ResponseEntity.ok().body(productSupplyService.editProductSupply(productSupply, id));
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
            return ResponseEntity.ok().body(productSupplyService.removeProductSupply(id));
        }
        catch(NoSuchElementException e){
            return ResponseEntity.status(404).body(new ErrorResponseDTO(e.getMessage()));
        }
        catch (RuntimeException e){
            return ResponseEntity.status(412).body(new ErrorResponseDTO(e.getMessage()));
        }
    }
}
