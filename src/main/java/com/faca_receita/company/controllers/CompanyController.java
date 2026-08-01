package com.faca_receita.company.controllers;

import com.faca_receita.company.dtos.CompanyResponse;
import com.faca_receita.company.dtos.CreateCompanyRequest;
import com.faca_receita.company.services.CompanyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping
    public ResponseEntity<CompanyResponse> create(@RequestBody @Valid CreateCompanyRequest company) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.companyService.create(company));
    }

    @GetMapping
    public ResponseEntity<CompanyResponse> findCompany() {
        return ResponseEntity.ok(this.companyService.findCompanyByUserId());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyResponse> update(@PathVariable Long id, @RequestBody CreateCompanyRequest company) {
        return ResponseEntity.ok(this.companyService.update(id, company));
    }
}
