package com.home.dreamcar.service;

import com.home.dreamcar.model.Company;
import com.home.dreamcar.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceDefault implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public Company findCompanyByEmail(String email) {
        return companyRepository.findCompanyByEmail(email);
    }

    @Override
    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }
}
