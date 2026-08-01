package com.faca_receita.auth.models;

import com.faca_receita.user.models.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class UserToken {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    @SequenceGenerator(name = "global_seq", sequenceName = "global_sequence", allocationSize = 1)
    private Long id;

    @Column(name = "token")
    private String token;

    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @Column(name = "used")
    private Boolean used;

    public UserToken(User user, LocalDateTime expirationDate) {
        this.token = UUID.randomUUID().toString();
        this.expirationDate = expirationDate;
        this.user = user;
        this.used = false;
    }

}
