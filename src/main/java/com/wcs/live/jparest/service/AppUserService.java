package com.wcs.live.jparest.service;

import com.wcs.live.jparest.dao.AppUserDao;
import com.wcs.live.jparest.dao.BaseDao;
import com.wcs.live.jparest.model.AppUser;
import com.wcs.live.jparest.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;


@Service
public class AppUserService extends AbstractBaseService<AppUser>
        implements UserDetailsService {

    @Autowired
    private AppUserDao dao;

    @Override
    public BaseDao<AppUser> getDao() {
        return dao;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<AppUser> maybeUser = dao.findByUsername(username);
        User u = maybeUser
                .map(user -> new User(user.getUsername(), user.getPasswordHash(), Collections.emptyList()))
                .orElseThrow(() -> new UsernameNotFoundException(""));
        return u;
    }
}