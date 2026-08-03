package com.faca_receita.company.mapper;

import com.faca_receita.company.dtos.CompanyRequest;
import com.faca_receita.company.dtos.CompanyResponse;
import com.faca_receita.company.models.Company;
import com.faca_receita.configs.MapperConfiguration;
import org.mapstruct.*;

@Mapper(config = MapperConfiguration.class)
public interface CompanyMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Company toEntity(CompanyRequest request);

    @Mapping(target = "userId", source = "user.id")
    CompanyResponse toResponse(Company company);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntity(CompanyRequest request, @MappingTarget Company company);
}
