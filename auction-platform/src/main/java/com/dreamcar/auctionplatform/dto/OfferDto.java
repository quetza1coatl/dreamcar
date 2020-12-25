package com.dreamcar.auctionplatform.dto;

import lombok.*;

import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OfferDto extends AbstractBaseDto {
    private @NonNull BigDecimal price;

    @Size(max = 200)
    private String description;

    @Size(max = 45)
    private @NonNull String supplierEmail;

    private @NonNull Integer requestId;

    @Size(max = 45)
    private @NonNull String status;

    private boolean isEditable;

    private boolean isApplying;
}
