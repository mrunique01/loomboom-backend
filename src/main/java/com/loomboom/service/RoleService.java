package com.loomboom.service;

import com.loomboom.enums.RoleEnum;
import com.loomboom.model.Role;

public interface RoleService {

    Role findByName(RoleEnum role);

    Role save(RoleEnum role);

}
