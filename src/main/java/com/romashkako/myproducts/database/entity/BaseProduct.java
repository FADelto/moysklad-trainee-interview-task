package com.romashkako.myproducts.database.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@MappedSuperclass
//Спорная реализация
public abstract class BaseProduct {
    @Column(name = "name", nullable = false)
    @Schema(description = "Название продукта. Максимальная длина 255 символов", maxLength = 255, example = "Чайник", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name = "";

    @Column(name = "description", length = 4096)
    @Schema(description = "Описание продукта. Максимальная длина 4096 символов", maxLength = 4096, example = "Электрический чайник с функцией поддержания температуры.")
    private String description = "";

    @Column(name = "price")
    @DecimalMin(value = "0.00")
    @Schema(description = "Цена продукта. Цена продукта не может быть отрицательной", defaultValue = "0.0", example = "1499.99")
    private Double price = 0.0;

    @Column(name = "inStock")
    @Schema(description = "Доступность продукта на складе", defaultValue = "false")
    private Boolean inStock = false;

}
