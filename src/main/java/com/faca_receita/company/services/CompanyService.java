package com.faca_receita.company.services;

import com.faca_receita.company.dtos.CompanyResponse;
import com.faca_receita.company.dtos.CreateCompanyRequest;
import com.faca_receita.company.mapper.CompanyMapper;
import com.faca_receita.company.models.Company;
import com.faca_receita.company.repositories.CompanyRepository;
import com.faca_receita.company.validation.CompanyValidators;
import jakarta.persistence.EntityNotFoundException;
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
    public CompanyResponse create(CreateCompanyRequest companyResquest) {
        this.companyValidators.validate(companyResquest);
        Company company = this.companyMapper.toEntity(companyResquest);
        company.setUserId(123L);
        company.setActive(true);
        company.setCreatedAt(LocalDateTime.now());

        this.companyRepository.save(company);
        return companyMapper.toResponse(company);
    }

    public CompanyResponse findCompanyByUserId() {
        Long userId = 123L;//getAuthenticatedUserId()
        Company company = this.companyRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("Empresa não encontrada"));
        return this.companyMapper.toResponse(company);
    }


    public CompanyResponse update(Long id, CreateCompanyRequest request) {
        this.companyValidators.validate(request);
        Company company = this.findById(id);
        this.companyMapper.updateEntity(request, company);
        company.setUpdatedAt(LocalDateTime.now());
        companyRepository.save(company);
        return companyMapper.toResponse(company);
    }

    public Company findById(Long id) {
        return this.companyRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada"));
    }
}
