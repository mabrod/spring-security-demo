package com.brodma.web.security.dao;

import com.brodma.web.security.domain.entity.LoginUser;

public interface LoginUsersDao {

    LoginUser findByUserName(String userName);

}
