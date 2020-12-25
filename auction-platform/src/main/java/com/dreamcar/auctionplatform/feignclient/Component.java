package com.dreamcar.auctionplatform.feignclient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Component {
    @Size(max = 45)
    private String name;

    private Integer quantity;
}
