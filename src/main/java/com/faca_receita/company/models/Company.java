package com.faca_receita.company.models;

import com.faca_receita.user.models.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "company")
@Getter
@Setter
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "legal_name")
    private String legalName;

    @Column(name = "trade_name")
    private String tradeName;

    @Column(name = "description")
    private String description;

    @Column(name = "cnpj")
    private String cnpj;

    @Column(name = "state_tax_registration")
    private String stateTaxRegistration;

    @Column(name = "municipal_tax_registration")
    private String municipalTaxRegistration;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "whatsapp")
    private String whatsapp;

    @Column(name = "email")
    private String email;

    @Column(name = "website")
    private String website;

    @Column(name = "instagram")
    private String instagram;

    @Column(name = "facebook")
    private String facebook;

    @Column(name = "logo_url")
    private String logoUrl;

    @Column(name = "primary_Color")
    private String primaryColor;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
