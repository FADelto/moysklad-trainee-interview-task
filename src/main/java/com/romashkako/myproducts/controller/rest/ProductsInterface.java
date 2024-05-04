package com.romashkako.myproducts.controller.rest;

import com.romashkako.myproducts.database.dto.ErrorResponseDTO;
import com.romashkako.myproducts.database.entity.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
@Tag(name="Контроллер продуктов")
public interface ProductsInterface {
    @Operation(
            summary = "Создать продукт"
    )
    @ApiResponse(responseCode = "200", description = "OK", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))
    })
    @ApiResponse(responseCode = "412", description = "Цена не может быть отрицательной | Описание товара не соответствует требованиям | Название товара не соответствует требованиям", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))
    })
    @PostMapping("/create")
    ResponseEntity<?> create(@Validated @RequestBody Product product);

    @Operation(
            summary = "Получить все продукты"
    )
    @ApiResponse(responseCode = "200", description = "OK", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Product.class)))
    })
    @GetMapping("/getall")
    ResponseEntity<?> getAll();

    @Operation(
            summary = "Получить продукт по id"
    )
    @ApiResponse(responseCode = "200", description = "OK", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))
    })
    @GetMapping("/getById")
    ResponseEntity<?> getById(int id);

    @Operation(
            summary = "Получить продукт по имени"
    )
    @ApiResponse(responseCode = "200", description = "OK", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))
    })
    @GetMapping("/getByName")
    ResponseEntity<?> getByName(String name);

    @Operation(
            summary = "Обновить продукт"
    )
    @ApiResponse(responseCode = "200", description = "OK", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))
    })
    @ApiResponse(responseCode = "404", description = "Неверный id продукта", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))
    })
    @ApiResponse(responseCode = "412", description = "Цена не может быть отрицательной | Описание товара не соответствует требованиям | Название товара не соответствует требованиям", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))
    })
    @PatchMapping("/update")
    ResponseEntity<?> update(@Validated @RequestBody Product product, @RequestParam int id);

    @Operation(
            summary = "Удалить продукт по id"
    )
    @ApiResponse(responseCode = "200", description = "OK", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class))
    })
    @ApiResponse(responseCode = "404", description = "Неверный id продукта", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))
    })
    @DeleteMapping("/remove")
    ResponseEntity<?> remove(int id);
}
