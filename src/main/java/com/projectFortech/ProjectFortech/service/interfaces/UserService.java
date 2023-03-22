package com.projectFortech.ProjectFortech.service.interfaces;

import com.projectFortech.ProjectFortech.domain.User;

import java.util.List;

public interface UserService {
    public User addUser(User user);
    public List<User> findAllUsers();
    public void deleteUserById(Integer id);
    public User findUserById(Integer id);
    public User findUserByEmail(String email);

}
