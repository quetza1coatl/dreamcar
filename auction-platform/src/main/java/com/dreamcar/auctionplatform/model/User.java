package com.dreamcar.auctionplatform.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends AbstractBaseEntity {
    @Email
    @Size(max = 45)
    @Column(unique = true, nullable = false, name = "user_email")
    private String email;

    @OneToOne(fetch = FetchType.LAZY)
    private Role role;
}
