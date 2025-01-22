package org.example.controllers;

import org.example.models.User;
import org.example.services.UserService;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class UserController extends HttpServlet
{
    private UserService userService;
    @Override
    public void init() throws ServletException
    {
        userService = new UserService();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String requestBody = request.getReader().readLine();
        Gson gson = new Gson();
        User user = gson.fromJson(requestBody, User.class);
        try {
            userService.registerUser(user);
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("{\"message\": \"Користувач зареєстрований!\"}");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"Помилка при реєстрації!\"}");
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = userService.loginUser(login, password);
        if (user != null)
        {
            response.setContentType("application/json");
            response.getWriter().write(new Gson().toJson(user));
        }
        else
        {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write("{\"error\": \"Користувач не знайдений!\"}");
        }
    }
}
