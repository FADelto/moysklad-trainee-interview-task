package com.romashkako.myproducts.database.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

@Value
public class ProductSaleDTO {
    @Schema(description = "Название документа. Максимальная длина 255 символов", maxLength = 255, example = "Продажа", requiredMode = Schema.RequiredMode.REQUIRED)
    String documentName;

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
    Long productId;

    @Schema(description = "Количество проданного товара", minimum = "0", requiredMode = Schema.RequiredMode.REQUIRED)
    Integer quantityOfSoldProducts;
}
