package com.dreamcar.auctionplatform.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Request extends AbstractBaseEntity {
    @Size(max = 45)
    @Column(nullable = false)
    private @NonNull String partName;

    @Column(nullable = false)
    @Min(value=0)
    private  @NonNull Integer quantity;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime creationDate;

    private LocalDateTime expirationDate;

    @Size(max = 200)
    private String description;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private @NonNull User customer;

    @OneToOne(optional = false)
    private @NonNull RequestStatus requestStatus;
}
