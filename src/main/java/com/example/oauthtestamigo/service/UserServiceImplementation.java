package com.example.oauthtestamigo.service;

import com.example.oauthtestamigo.model.MyUser;
import com.example.oauthtestamigo.model.Role;
import com.example.oauthtestamigo.repo.RoleRepository;
import com.example.oauthtestamigo.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImplementation implements UserService, UserDetailsService {
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;

    @Override
    public MyUser saveUser(MyUser myUser) {
        log.info("saving new user");
        return userRepo.save(myUser);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("saving new role {} to the database", role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        log.info("Adding role {} to user {}", roleName,userName);
        MyUser myUser = userRepo.findByUserName(userName);
        Role role = roleRepo.findByName(roleName);
        myUser.getRoles().add(role);
    }

    @Override
    public MyUser getUser(String userName) {
        log.info("Fetching user {}", userName);
        return userRepo.findByUserName(userName);
    }

    @Override
    public List<MyUser> getUsers() {
        return userRepo.findAll();
    }
    @Override
    public UserDetails loadUserByUsername(String s){
        MyUser myUser = userRepo.findByUserName(s);
        if(myUser == null){
            log.error("User is not in database");
            throw new UsernameNotFoundException("User is not in database");
        }
        else{
            log.info("User found in database");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : myUser.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new org.springframework.security.core.userdetails.User(myUser.getUserName(), myUser.getPassword(), authorities);
    }
}
