package com.springboot.thymeleaf.dao;


import com.springboot.thymeleaf.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{

    Page<User> findByNameLikeOrPhone(String name, String phone, Pageable pageable);

    User findByPhoneAndPassword(String phone, String password);
}
