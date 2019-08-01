package com.telran.telranshopspringdata.service;


import com.telran.telranshopspringdata.data.UserDetailsRepository;
import com.telran.telranshopspringdata.data.entity.UserDetailsEntity;
import com.telran.telranshopspringdata.data.entity.UserRoleEntity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

public class CustomUserDetailsService implements UserDetailsService {
    UserDetailsRepository repository;

    public CustomUserDetailsService(UserDetailsRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetailsEntity entity = repository.findById(username).orElse(null);
        if (entity == null) {
            throw new UsernameNotFoundException(String.format("User with name: %s doesn't exist!", username));
        }
        String[] roles = entity.getRoles().stream()
                .map(UserRoleEntity::getRole)
                .toArray(String[]::new);
        return User.builder()
                .username(entity.getEmail())
                .password(entity.getPassword())
                .authorities(AuthorityUtils.createAuthorityList(roles))
                .build();
    }
}
