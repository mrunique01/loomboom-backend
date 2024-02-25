package com.loomboom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loomboom.model.User;
import com.loomboom.model.UserAddress;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {
    List<UserAddress> findByUsers(User user);

    Optional<UserAddress> findByIdAndUsersId(Long id, Long userId);

}
