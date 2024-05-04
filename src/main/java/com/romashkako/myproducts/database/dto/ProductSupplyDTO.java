package com.romashkako.myproducts.database.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

@Value
public class ProductSupplyDTO {

    @Schema(description = "Название документа. Максимальная длина 255 символов", maxLength = 255, example = "Поставка", requiredMode = Schema.RequiredMode.REQUIRED)
    String documentName;

    @Schema(description = "Id продукта", requiredMode = Schema.RequiredMode.REQUIRED)
    Long productId;

    @Schema(description = "Количество поставленного товара", minimum = "0", requiredMode = Schema.RequiredMode.REQUIRED)
    Integer quantityOfDeliveredProducts;
}
