package com.dreamcar.auctionplatform.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
public class RequestStatus extends AbstractBaseEntity{
    @Size(max = 45)
    @Column(unique = true, nullable = false, name = "request_status_name")
    private String name;
}
