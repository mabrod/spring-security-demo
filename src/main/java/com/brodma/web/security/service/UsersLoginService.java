package com.brodma.web.security.service;

import com.brodma.web.security.dao.LoginUsersDao;
import com.brodma.web.security.domain.AccountDetails;
import com.brodma.web.security.domain.entity.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsersLoginService implements UserDetailsService {

    @Autowired
    private LoginUsersDao usersDao;

    @Autowired
    private InMemoryLoginAttemptsService loginAttemptService;


    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (StringUtils.isEmpty(username)) {
            throw new UsernameNotFoundException(username);
        }

        if (loginAttemptService.reachedLimit(username)) {
            throw new LockedException(username);
        }

        try {

            final LoginUser user = usersDao.findByUserName(username);
            AccountDetails accountDetails = new AccountDetails(user);
            return accountDetails;

        }catch (DataAccessException dae) {
            throw dae;
        }
    }
}
