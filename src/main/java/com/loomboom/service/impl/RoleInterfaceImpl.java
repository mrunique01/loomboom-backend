package com.loomboom.service.impl;

import org.springframework.stereotype.Service;

import com.loomboom.enums.RoleEnum;
import com.loomboom.model.Role;
import com.loomboom.repository.RoleRepository;
import com.loomboom.service.RoleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleInterfaceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role findByName(RoleEnum role) {
        return roleRepository.findByName(role);
    }

    @Override
    public Role save(RoleEnum role) {
        Role roleObj = new Role();
        roleObj.setName(role);
        return roleRepository.save(roleObj);
    }

}
