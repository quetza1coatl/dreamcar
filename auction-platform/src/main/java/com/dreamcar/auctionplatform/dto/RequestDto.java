package com.dreamcar.auctionplatform.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestDto extends AbstractBaseDto {

    @Size(max = 45)
    private @NonNull String partName;

    @Min(value=0)
    private @NonNull Integer quantity;

    @Size(max = 200)
    private String description;

    private @NonNull Integer customerId;

    private @NonNull Integer requestStatusId;
}
