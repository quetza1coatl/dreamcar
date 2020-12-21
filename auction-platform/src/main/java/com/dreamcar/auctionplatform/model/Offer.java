package com.dreamcar.auctionplatform.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
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

    @OneToOne(optional = false)
    private @NonNull User supplier;

    @OneToOne(optional = false)
    private @NonNull Request request;

    @OneToOne(optional = false)
    private @NonNull OfferStatus offerStatus;

    public Offer(
            @NonNull BigDecimal price, @Size(max = 200) String description, @NonNull User supplier,
            @NonNull Request request, @NonNull OfferStatus offerStatus
    ) {
        this(price, supplier,request, offerStatus);
        this.description = description;
    }
}
