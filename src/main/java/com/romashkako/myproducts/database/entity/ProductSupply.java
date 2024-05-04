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
@Table(name = "product_supply")
public class ProductSupply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @Schema(description = "Название документа. Максимальная длина 255 символов", maxLength = 255, example = "Поставка", requiredMode = Schema.RequiredMode.REQUIRED)
    String documentName;

    @ManyToOne
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    Product product;

    @Min(value = 0)
    @Schema(description = "Количество поставленного товара", requiredMode = Schema.RequiredMode.REQUIRED)
    Integer quantityOfDeliveredProducts;

    public ProductSupply(String documentName, Product product, Integer quantityOfDeliveredProducts) {
        this.documentName = documentName;
        this.product = product;
        this.quantityOfDeliveredProducts = quantityOfDeliveredProducts;
    }
}
