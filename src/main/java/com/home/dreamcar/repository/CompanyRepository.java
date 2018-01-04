package com.home.dreamcar.repository;

import com.home.dreamcar.model.Auction;
import com.home.dreamcar.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findCompanyByEmail(String email);
}
