package com.romashkako.myproducts.database.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
//public class Product extends BaseProduct {
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @Column(name = "in_stock")
    @Schema(description = "Доступность продукта на складе", defaultValue = "false")
    private Boolean inStock = false;

    @Schema(description = "Количество продукта на складе", defaultValue = "0")
    private Integer quantityOfProducts=0;
    public Product(String name, String description, Double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
    //    public Product(String name, String description, Double price, Boolean inStock){
//        super(name, description, price, inStock);
//    }
}
