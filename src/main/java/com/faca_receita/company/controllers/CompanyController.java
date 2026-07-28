package com.faca_receita.company.controllers;

import com.faca_receita.company.dtos.CreateCompanyResponse;
import com.faca_receita.company.dtos.CreateCompanyRequest;
import com.faca_receita.company.services.CompanyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping
    public ResponseEntity<CreateCompanyResponse> create(@RequestBody @Valid CreateCompanyRequest company) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.companyService.create(company));
    }
}
