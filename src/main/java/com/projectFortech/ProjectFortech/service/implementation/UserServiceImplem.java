package com.projectFortech.ProjectFortech.service.implementation;

import com.projectFortech.ProjectFortech.domain.User;
import com.projectFortech.ProjectFortech.exceptions.UnfoundUserException;
import com.projectFortech.ProjectFortech.repository.UserRepository;
import com.projectFortech.ProjectFortech.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImplem implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImplem(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll().stream().filter(user -> !user.getRole().equals("Manager")).toList();
    }


    @Override
    public User findUserById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new UnfoundUserException("User by id " + id + " was not found"));
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UnfoundUserException("This account doesn't exist!"));
    }


    @Override
    public void deleteUserById(Integer id) {
        userRepository.deleteById(id);
    }


}
