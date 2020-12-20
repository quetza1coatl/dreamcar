package com.dreamcar.auctionplatform.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
public class OfferStatus extends AbstractBaseEntity{
    @Size(max = 45)
    @Column(unique = true, nullable = false, name = "offer_status_name")
    private String name;

}
