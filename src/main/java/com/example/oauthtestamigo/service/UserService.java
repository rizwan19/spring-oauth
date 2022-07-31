package com.example.oauthtestamigo.service;

import com.example.oauthtestamigo.model.MyUser;
import com.example.oauthtestamigo.model.Role;

import java.util.List;

public interface UserService {
    MyUser saveUser(MyUser myUser);
    Role saveRole(Role role);
    void addRoleToUser(String userName, String roleName);
    MyUser getUser(String userName);
    List<MyUser>getUsers();
}
