package com.example.oauthtestamigo.controller;

import com.example.oauthtestamigo.model.MyUser;
import com.example.oauthtestamigo.model.Role;
import com.example.oauthtestamigo.model.RoleToUserForm;
import com.example.oauthtestamigo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/users")
    public ResponseEntity<List<MyUser>> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }
    @PostMapping("/user/save")
    public ResponseEntity<MyUser> saveUser(@RequestBody MyUser myUser){
        return ResponseEntity.ok().body(userService.saveUser(myUser));
    }
    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        return ResponseEntity.ok().body(userService.saveRole(role));
    }
    @PostMapping("role/addtouser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm roleToUserForm ){
        userService.addRoleToUser(roleToUserForm.getUserName(),roleToUserForm.getRoleName());
        return ResponseEntity.ok().build();
    }

}
