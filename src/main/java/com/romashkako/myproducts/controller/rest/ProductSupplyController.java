package com.romashkako.myproducts.controller.rest;

import com.romashkako.myproducts.database.dto.ErrorResponseDTO;
import com.romashkako.myproducts.database.dto.ProductSupplyDTO;
import com.romashkako.myproducts.database.entity.ProductSupply;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Контролер поставок")
public interface ProductSupplyController{

    @Operation(
            summary = "Создать поставку"
    )
    @ApiResponse(responseCode = "200", description = "OK", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ProductSupply.class))
    })
    @ApiResponse(responseCode = "404", description = "Поставка товара с таким id не найдена | Неверный id продукта", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))
    })
    @ApiResponse(responseCode = "412", description = "Количество товара не может быть меньше 0 | Количество поставленных товаров не может быть отрицательным или равняться нулю | Название документа не соответствует требованиям", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))
    })
    @PostMapping("/create")
    ResponseEntity<?> create(@Validated @RequestBody ProductSupplyDTO productSupply);

    @Operation(
            summary = "Получить все поставки"
    )
    @ApiResponse(responseCode = "200", description = "OK", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProductSupply.class)))
    })
    @GetMapping("/getall")
    ResponseEntity<?> getAll();

    @Operation(
            summary = "Получить поставку по id"
    )
    @ApiResponse(responseCode = "200", description = "OK", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ProductSupply.class))
    })
    @GetMapping("/getById")
    ResponseEntity<?> getById(Long id);

    @Operation(
            summary = "Обновить поставку"
    )
    @ApiResponse(responseCode = "200", description = "OK", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ProductSupply.class))
    })
    @ApiResponse(responseCode = "404", description = "Поставка товара с таким id не найдена | Неверный id продукта", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))
    })
    @ApiResponse(responseCode = "412", description = "Количество товара не может быть меньше 0 | Количество поставленных товаров не может быть отрицательным или равняться нулю | Название документа не соответствует требованиям", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))
    })
    @PatchMapping("/update")
    ResponseEntity<?> update(@Validated @RequestBody ProductSupplyDTO productSupply, @RequestParam Long id);

    @Operation(
            summary = "Удалить поставку по id"
    )
    @ApiResponse(responseCode = "200", description = "OK", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ProductSupply.class))
    })
    @ApiResponse(responseCode = "404", description = "Поставка товара с таким id не найдена | Неверный id продукта", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))
    })
    @ApiResponse(responseCode = "412", description = "Количество товара не может быть меньше 0", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseDTO.class))
    })
    @DeleteMapping("/remove")
    ResponseEntity<?> remove(Long id);
}
