package com.romashkako.myproducts.database.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product_sales")
public class ProductSale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @Schema(description = "Название документа. Максимальная длина 255 символов", maxLength = 255, example = "Продажа", requiredMode = Schema.RequiredMode.REQUIRED)
    String documentName;

    @ManyToOne
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    Product product;

    @Min(value = 0)
    @Schema(description = "Количество проданного товара", requiredMode = Schema.RequiredMode.REQUIRED)
    Integer quantityOfSoldProducts;

    @Min(value = 0)
    @Schema(description = "Стоимость покупки", requiredMode = Schema.RequiredMode.REQUIRED)
    Double cost;

    public ProductSale(String documentName, Product product, Integer quantityOfSoldProducts, Double cost) {
        this.documentName = documentName;
        this.product = product;
        this.quantityOfSoldProducts = quantityOfSoldProducts;
        this.cost = cost;
    }
}
