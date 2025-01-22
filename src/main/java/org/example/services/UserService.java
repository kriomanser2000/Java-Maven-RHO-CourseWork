package org.example.services;

import org.example.dao.UserDAO;
import org.example.models.User;

public class UserService
{
    private UserDAO userDAO;
    public UserService()
    {
        this.userDAO = new UserDAO();
    }
    public void registerUser(User user)
    {
        if (userDAO.getUserByLogin(user.getLogin()) != null)
        {
            throw new RuntimeException("Користувач з таким логіном вже існує!");
        }
        userDAO.addUser(user);
    }
    public User loginUser(String login, String password)
    {
        User user = userDAO.getUserByLogin(login);
        if (user != null && user.getPassword().equals(password))
        {
            return user;
        }
        throw new RuntimeException("Невірний логін або пароль!");
    }
    public void updateUser(User user)
    {
        userDAO.updateUser(user);
    }
}
