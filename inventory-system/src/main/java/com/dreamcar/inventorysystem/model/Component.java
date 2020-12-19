package com.dreamcar.inventorysystem.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Component extends AbstractBaseEntity{
    @Size(max = 45)
    private String name;

    private Integer quantity;

    public Component(@Size(max = 45) String name, Integer quantity) {
        this.name = name;
        this.quantity = quantity;
    }
}
