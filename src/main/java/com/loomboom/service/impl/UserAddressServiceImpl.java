package com.loomboom.service.impl;

import com.loomboom.exceptions.ApiRequestException;
import com.loomboom.model.User;
import com.loomboom.model.UserAddress;
import com.loomboom.repository.UserAddressRepository;
import com.loomboom.service.UserAddressService;
import lombok.RequiredArgsConstructor;
import static com.loomboom.utils.StringUtils.*;

import java.util.List;

import static com.loomboom.contants.ErrorMessage.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAddressServiceImpl implements UserAddressService {

    private final UserAddressRepository userAddressRepository;

    @Override
    public List<UserAddress> getAllUserAddress() {
        return userAddressRepository.findAll();
    }

    @Override
    public Page<UserAddress> getAllUserAddressByPage(Pageable pageable) {
        return userAddressRepository.findAll(pageable);
    }

    @Override
    public UserAddress createUserAddress(UserAddress userAddress) {
        return userAddressRepository.save(userAddress);
    }

    @Override
    public UserAddress updateUserAddress(UserAddress userAddress, Long id) {
        if (findById(id) == null) {
            throw new ApiRequestException(ADSRESS_EXISTS, HttpStatus.BAD_REQUEST);
        }

        userAddress.setId(id);
        return userAddressRepository.save(userAddress);
    }

    @Override
    public Boolean deleteUserAddressById(Long id) {
        UserAddress userAddress = findById(id);
        if (userAddress == null) {
            throw new ApiRequestException(ADSRESS_EXISTS, HttpStatus.BAD_REQUEST);
        }
        userAddressRepository.deleteById(id);
        return true;

    }

    @Override
    public UserAddress findById(Long id) {
        if (!empty(id)) {
            return userAddressRepository.findById(id).orElse(null);
        }
        return null;
    }

    @Override
    public List<UserAddress> findByUsers(User user) {
        return userAddressRepository.findByUsers(user);
    }

}
