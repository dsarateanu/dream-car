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
    EmailService emailService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User saveAdminUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        user.setCompany(companyService.findCompanyByEmail("dreamcar@dreamcar.com"));
        emailService.sendMailToActivatedUser(user.getEmail());
        return userRepository.save(user);
    }

    @Override
    public User saveUser(User user, boolean active) {
        Company companyExists = companyService.findCompanyByEmail(user.getCompany().getEmail());
        if (companyExists == null) {
            companyService.saveCompany(user.getCompany());
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        if(active == true){
            user.setActive(1);
            emailService.sendMailToActivatedUser(user.getEmail());
        } else {
            user.setActive(0);
            emailService.sendMailToUnactivatedUser(user.getEmail());
        }
        return userRepository.save(user);
    }


    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }
}
