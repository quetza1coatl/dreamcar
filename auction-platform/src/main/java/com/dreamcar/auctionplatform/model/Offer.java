package com.dreamcar.auctionplatform.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Offer extends AbstractBaseEntity{
    @Column(nullable = false)
    private @NonNull BigDecimal price;

    @Size(max = 200)
    private String description;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private @NonNull User supplier;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private @NonNull Request request;

    @OneToOne(optional = false)
    private @NonNull OfferStatus offerStatus;
}
