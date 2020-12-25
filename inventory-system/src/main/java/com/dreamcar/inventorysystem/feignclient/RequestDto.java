package com.dreamcar.inventorysystem.feignclient;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class RequestDto{
    private @NonNull String partName;
    private @NonNull Integer quantity;
    private String description;
    private @NonNull String customerEmail = "customer@test.com";
    private String creationDate;
    private String expirationDate;
    private @NonNull String status = "draft";
    private boolean isEditable;
    private boolean isOfferCreated;
}
