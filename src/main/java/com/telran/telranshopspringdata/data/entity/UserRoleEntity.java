package com.telran.telranshopspringdata.data.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "roles")
public class UserRoleEntity {
    @Id
    private String role;
    @ManyToMany(mappedBy = "roles")
    private List<UserDetailsEntity> users;
}
