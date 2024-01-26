package com.loomboom.service.impl;

import com.loomboom.enums.RoleEnum;
import com.loomboom.model.Role;
import com.loomboom.repository.RoleRepository;
import com.loomboom.service.RoleService;

public class RoleInterfaceImpl implements RoleService {

    RoleRepository roleRepository;

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
