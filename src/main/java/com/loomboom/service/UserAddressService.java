package com.loomboom.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.loomboom.model.User;
import com.loomboom.model.UserAddress;

public interface UserAddressService {

    List<UserAddress> getAllUserAddress();

    List<UserAddress> findByUser(User user);

    Page<UserAddress> getAllUserAddressByPage(Pageable pageable);

    UserAddress createUserAddress(UserAddress userAddress);

    UserAddress updateUserAddress(UserAddress userAddress, Long id);

    Boolean deleteUserAddressById(Long id);

    UserAddress findById(Long id);

    Boolean deleteUserAddressByUserId(Long id, Long userId);

    UserAddress updateUserAddressByUserId(UserAddress userAddress, Long id, Long userId);

}