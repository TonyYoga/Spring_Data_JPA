package com.telran.telranshopspringdata.service;

import com.telran.telranshopspringdata.data.UserDetailsRepository;
import com.telran.telranshopspringdata.data.entity.UserDetailsEntity;
import com.telran.telranshopspringdata.data.entity.UserRoleEntity;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

    @Autowired
    UserDetailsRepository repository;
    @Autowired
    PasswordEncoder encoder;
    @Override
    public void registration(String email, String password) {
        if (repository.existsById(email)) {
            throw new ServiceException("User already exist!");
        }
        UserDetailsEntity entity = UserDetailsEntity.builder()
                .email(email)
                .password(encoder.encode(password))
                .roles(List.of(UserRoleEntity.builder()
                        .role("ROLE_USER")
                        .build())
                ).build();
        repository.save(entity);
    }
}
