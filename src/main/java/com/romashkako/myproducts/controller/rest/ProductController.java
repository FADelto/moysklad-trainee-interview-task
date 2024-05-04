package com.romashkako.myproducts.controller.rest;

import com.romashkako.myproducts.database.dto.ErrorResponseDTO;
import com.romashkako.myproducts.database.dto.ProductDTO;
import com.romashkako.myproducts.database.entity.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
@Tag(name="Контроллер продуктов")
public interface ProductController {
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
    ResponseEntity<?> create(@Validated @RequestBody ProductDTO product);

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
    ResponseEntity<?> getById(Long id);


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
    ResponseEntity<?> update(@Validated @RequestBody ProductDTO product, @RequestParam Long id);

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
    ResponseEntity<?> remove(Long id);

    @Operation(
            summary = "Поиск продуктов по фильтру и сортировка с ограничением выдачи"
    )
    @ApiResponse(responseCode = "200", description = "OK", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Product.class)))
    })
    @GetMapping("/filter")
    ResponseEntity<?> filter(@Parameter(description = "Поиск по имени продукта") @RequestParam(required = false) String name,
                             @Parameter(description = "Минимальная цена продукта") @RequestParam(required = false) Double minPrice,
                             @Parameter(description = "Максимальная цена продукта") @RequestParam(required = false) Double maxPrice,
                             @Parameter(description = "Поиск по наличию продукта") @RequestParam(required = false) Boolean inStock,
                             @Parameter(description = "Сортировка продукта по имени(name) или цене(price)") @RequestParam(required = false) String orderSelect,
                             @Parameter(description = "Выбор направления сортировки по возрастанию(true) или по убыванию(false)") @RequestParam(required = false) Boolean orderDirectionAsc,
                             @Parameter(description = "Лимит выборки записей") @RequestParam(required = false) Integer limit);

}
