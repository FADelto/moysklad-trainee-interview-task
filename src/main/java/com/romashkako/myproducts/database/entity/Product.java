package com.romashkako.myproducts.database.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Product {
    @Schema(description = "Имя продукта. Максимальная длина 255 символов", maxLength = 255, example = "Чайник", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name = "";

    @Schema(description = "Описание продукта. Максимальная длина 4096 символов", maxLength = 4096, example = "Электрический чайник с функцией поддержания температуры.")
    private String description = "";

    @Schema(description = "Цена продукта. Цена продукта не может быть отрицательной", minimum = "0", defaultValue = "0.0", example = "1499.99")
    private Double price = 0.0;

    @Schema(description = "Доступность продукта на складе", defaultValue = "false")
    private Boolean inStock = false;
}
