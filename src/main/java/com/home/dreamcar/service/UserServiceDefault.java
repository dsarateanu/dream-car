package com.home.dreamcar.service;

import com.home.dreamcar.model.Company;
import com.home.dreamcar.model.User;
import com.home.dreamcar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceDefault implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User saveAdminUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        user.setRole("ADMIN");
        user.setCompany(companyService.findCompanyByEmail("dream.car@dreamcar.com"));
        return userRepository.save(user);
    }

    @Override
    public User saveUser(User user) {
        Company companyExists = companyService.findCompanyByEmail(user.getCompany().getEmail());
        if (companyExists == null) {
            companyService.saveCompany(user.getCompany());
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(0);
        return userRepository.save(user);
    }

    @Override
    public User approveUser(User user) {
        user.setActive(0);
        return userRepository.save(user);
    }
}
