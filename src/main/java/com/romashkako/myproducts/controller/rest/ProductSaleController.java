package com.romashkako.myproducts.controller.rest;

import com.romashkako.myproducts.database.dto.ErrorResponseDTO;
import com.romashkako.myproducts.database.dto.ProductSaleDTO;
import com.romashkako.myproducts.database.entity.ProductSale;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Контролер продаж")
public interface ProductSaleController {

    @Operation(
            summary = "Создать продажу"
    )
    @ApiResponse(responseCode = "200", description = "OK", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ProductSale.class))
    })
    @ApiResponse(responseCode = "404", description = "Продажа товара с таким id не найдена | Неверный id продукта", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))
    })
    @ApiResponse(responseCode = "412", description = "Количество товара не может быть меньше 0 | Количество проданных товаров не может быть отрицательным или равняться нулю | Название документа не соответствует требованиям", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))
    })
    @PostMapping("/create")
    ResponseEntity<?> create(@Validated @RequestBody ProductSaleDTO productSale);

    @Operation(
            summary = "Получить все продажи"
    )
    @ApiResponse(responseCode = "200", description = "OK", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProductSale.class)))
    })
    @GetMapping("/getall")
    ResponseEntity<?> getAll();

    @Operation(
            summary = "Получить информацию о продаже по id"
    )
    @ApiResponse(responseCode = "200", description = "OK", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ProductSale.class))
    })
    @GetMapping("/getById")
    ResponseEntity<?> getById(Long id);

    @Operation(
            summary = "Обновить информацию о продаже"
    )
    @ApiResponse(responseCode = "200", description = "OK", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ProductSale.class))
    })
    @ApiResponse(responseCode = "404", description = "Продажа товара с таким id не найдена | Неверный id продукта", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))
    })
    @ApiResponse(responseCode = "412", description = "Количество товара не может быть меньше 0 | Количество проданных товаров не может быть отрицательным или равняться нулю | Название документа не соответствует требованиям", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))
    })
    @PatchMapping("/update")
    ResponseEntity<?> update(@Validated @RequestBody ProductSaleDTO productSale, @RequestParam Long id);

    @Operation(
            summary = "Удалить продажу по id"
    )
    @ApiResponse(responseCode = "200", description = "OK", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ProductSale.class))
    })
    @ApiResponse(responseCode = "404", description = "Продажа товара с таким id не найдена | Неверный id продукта", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))
    })
    @ApiResponse(responseCode = "412", description = "Количество товара не может быть меньше 0", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))
    })
    @DeleteMapping("/remove")
    ResponseEntity<?> remove(Long id);
}
