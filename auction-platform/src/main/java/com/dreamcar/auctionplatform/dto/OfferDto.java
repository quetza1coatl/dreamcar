package com.dreamcar.auctionplatform.dto;

import lombok.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OfferDto extends AbstractBaseDto{
    private @NonNull BigDecimal price;

    @Size(max = 200)
    private String description;

    private @NonNull Integer supplierId;

    private @NonNull Integer requestId;

    private @NonNull Integer offerStatusId;
}
