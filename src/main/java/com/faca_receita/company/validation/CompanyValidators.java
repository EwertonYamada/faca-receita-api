package com.faca_receita.company.validation;

import com.faca_receita.company.dtos.CompanyRequest;
import com.faca_receita.company.repositories.CompanyRepository;
import org.springframework.stereotype.Component;

@Component
public class CompanyValidators {

    private final CompanyRepository companyRepository;

    public CompanyValidators(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public void validate(CompanyRequest companyRequest) {
//        if (this.companyRepository.existsByUserId(companyRequest.userId())) throw new RuntimeException("Usuário já possui empresa cadastrada");
//        if (companyRequest.cnpj() != null && this.companyRepository.existsByCnpj(companyRequest.cnpj())) throw new RuntimeException("CNPJ já cadastrado.");
    }
}
