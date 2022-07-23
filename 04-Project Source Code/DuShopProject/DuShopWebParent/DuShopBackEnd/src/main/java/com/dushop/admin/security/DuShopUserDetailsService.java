package com.dushop.admin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.dushop.admin.user.UserRepository;
import com.dushop.common.entity.User;

/*
 *@BelongsProject: DuShopProject
 *@BelongsPackage: com.dushop.admin.security
 *@Author: Jiang Chufeng
 *@CreateTime: 2022-07-22  20:20
 *@Description: TODO
 *@Version: 1.0
 */

/*public class DuShopUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.getUserByEmail(email);
        if (user != null) {
            return new DuShopUserDetails(user);
        }

        throw new UsernameNotFoundException("Could not find user with email: " + email);
    }

}*/

public class DuShopUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.getUserByEmail(email);
        if (user != null) {
            return new DuShopUserDetails(user);
        }

        throw new UsernameNotFoundException("Could not find user with email: " + email);
    }

}