package com.dreamcar.auctionplatform.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role extends AbstractBaseEntity{
    @Size(max = 45)
    @Column(unique = true, nullable = false, name = "role_name")
    private String name;
}
