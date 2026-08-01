package com.faca_receita.company.mapper;

import com.faca_receita.company.dtos.CompanyResponse;
import com.faca_receita.company.dtos.CreateCompanyRequest;
import com.faca_receita.company.models.Company;
import com.faca_receita.configs.MapperConfiguration;
import org.mapstruct.*;

@Mapper(config = MapperConfiguration.class)
public interface CompanyMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Company toEntity(CreateCompanyRequest request);

    CompanyResponse toResponse(Company company);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntity(CreateCompanyRequest request, @MappingTarget Company company);
}
