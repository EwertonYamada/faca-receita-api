package com.faca_receita.company.services;

import com.faca_receita.company.dtos.CreateCompanyResponse;
import com.faca_receita.company.dtos.CreateCompanyRequest;
import com.faca_receita.company.mapper.CompanyMapper;
import com.faca_receita.company.models.Company;
import com.faca_receita.company.repositories.CompanyRepository;
import com.faca_receita.company.validation.CompanyValidators;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final CompanyValidators companyValidators;
    public CompanyService(CompanyRepository companyRepository, CompanyMapper companyMapper, CompanyValidators companyValidators) {
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
        this.companyValidators = companyValidators;
    }
    public CreateCompanyResponse create(CreateCompanyRequest companyResquest) {
        this.companyValidators.validate(companyResquest);
        Company company = this.companyMapper.toEntity(companyResquest);
        company.setUserId(123L);
        company.setActive(true);
        company.setCreatedAt(LocalDateTime.now());

        this.companyRepository.save(company);
        return companyMapper.toResponse(company);
    }
}
