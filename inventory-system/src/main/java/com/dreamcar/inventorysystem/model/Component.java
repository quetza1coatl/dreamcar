package com.dreamcar.inventorysystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Component extends AbstractBaseEntity{
    @Size(max = 45)
    private String name;

    private Integer quantity;
}
