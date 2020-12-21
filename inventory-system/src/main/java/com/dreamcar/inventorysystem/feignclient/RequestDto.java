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
    private Integer customerId = 1;
    private Integer requestStatusId = 1;
}
