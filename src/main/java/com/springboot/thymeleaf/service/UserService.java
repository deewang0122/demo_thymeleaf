package com.springboot.thymeleaf.service;

import com.springboot.thymeleaf.dao.UserRepository;
import com.springboot.thymeleaf.dto.UserDto;
import com.springboot.thymeleaf.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Page<User> index(String name, String phone, Pageable pageable) {
        Page<User> page = userRepository.findByNameLikeOrPhone("%"+name+"%", phone, pageable);
        return page;
    }

    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        return users;
    }

    public User findUserById(long id) {
        return userRepository.findOne(id);
    }

    public void save(User user) {
        userRepository.save(user);
    }


    public void edit(User user) {
        userRepository.saveAndFlush(user);
    }

    public void delete(long id) {
        userRepository.delete(id);
    }

    public Boolean isExist(String phone, String password) {
        if (!Objects.isNull(userRepository.findByPhoneAndPassword(phone, password))) {
            return true;
        }
        return false;
    }


    private User convert(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        return user;
    }

    private UserDto convert(User user) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }
}
