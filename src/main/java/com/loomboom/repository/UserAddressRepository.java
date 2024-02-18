package com.loomboom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loomboom.model.User;
import com.loomboom.model.UserAddress;
import java.util.List;

@Repository
public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {
    public List<UserAddress> findByUsers(User users);
}
