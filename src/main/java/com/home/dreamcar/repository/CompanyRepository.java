package com.home.dreamcar.repository;

import com.home.dreamcar.model.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {

    Company findCompanyByEmail(String email);
}
