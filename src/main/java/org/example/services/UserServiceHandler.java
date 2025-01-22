package org.example.services;

import org.example.models.User;

public class UserServiceHandler
{
    private UserService userService;

    public UserServiceHandler()
    {
        this.userService = new UserService();
    }
    public void registerUser(User user)
    {
        userService.registerUser(user);
    }
    public User loginUser(String login, String password)
    {
        return userService.loginUser(login, password);
    }
    public void updateUser(User user)
    {
        userService.updateUser(user);
    }
}