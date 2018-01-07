package com.home.dreamcar.service;

import com.home.dreamcar.model.Company;

public interface CompanyService {

    Company findCompanyByEmail(String email);

    Company saveCompany(Company company);
}
