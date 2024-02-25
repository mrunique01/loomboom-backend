package com.loomboom.model;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.loomboom.enums.AuthProvider;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
public class User implements UserDetails, OAuth2User {
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
        private String profilePhoto;

        @Enumerated(EnumType.STRING)
        private AuthProvider provider;
        
        @JsonManagedReference
        @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
        private List<Order> orders;
        @ManyToMany(fetch = FetchType.EAGER)
        @JsonManagedReference
        @JoinTable(name = "user_roles", joinColumns = {
                        @JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = {
                                        @JoinColumn(name = "role_id", referencedColumnName = "id")
                        })
        private List<Role> roles;

        @Transient
        private Map<String, Object> attributes;

        @JsonManagedReference
        @OneToMany(mappedBy = "users", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
        private List<UserAddress> userAddresses;

        @JsonManagedReference
        @OneToMany(mappedBy = "users", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
        private List<ShippingDetail> shippingDetails;

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
                return this.roles.stream().map(role -> new SimpleGrantedAuthority(role.getName().toString()))
                                .collect(Collectors.toList());
        }

        @Override
        public String getUsername() {
                return this.email;
        }

        @Override
        public Map<String, Object> getAttributes() {
                return this.attributes;
        }

        @Override
        public String getName() {
                return this.email;
        }
}
