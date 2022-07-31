package com.example.oauthtestamigo.repo;

import com.example.oauthtestamigo.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<MyUser, Long> {
    MyUser findByUserName(String userName);
}
