package com.brodma.web.security.dao;

import com.brodma.web.security.domain.entity.Role;
import java.util.List;

public interface RolesDao {

    List<Role> findAll();

    List<Role> findByUserId(Long userId);
}
