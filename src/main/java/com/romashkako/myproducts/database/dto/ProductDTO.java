package com.romashkako.myproducts.database.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Value;

@Value
//public class ProductDTO extends BaseProduct {
public class ProductDTO{
    @Schema(description = "Название продукта. Максимальная длина 255 символов", maxLength = 255, example = "Чайник")
    String name;

    @Schema(description = "Описание продукта. Максимальная длина 4096 символов", maxLength = 4096, example = "Электрический чайник с функцией поддержания температуры.")
    String description;

    @Schema(description = "Цена продукта. Цена продукта не может быть отрицательной", minimum = "0.0",defaultValue = "0.0", example = "1499.99")
    Double price;

    @Schema(description = "Доступность продукта на складе", defaultValue = "false")
    Boolean inStock;


}
