package com.loomboom.model;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User implements UserDetails {
        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        private Long id;
        private String firstName;
        private String lastName;
        private String gender;
        @Column(unique = true, nullable = false)
        private String email;
        private String password;
        private String phone;

        @ManyToMany
        @JoinTable(name = "user_roles", joinColumns = {
                        @JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = {
                                        @JoinColumn(name = "role_id", referencedColumnName = "id")
                        })
        private List<Role> roles;

        @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
        private List<Address> address;

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
                return this.roles.stream().map(role -> new SimpleGrantedAuthority(role.getName().toString()))
                                .collect(Collectors.toList());
        }

        @Override
        public String getUsername() {
                return this.email;
        }
}
