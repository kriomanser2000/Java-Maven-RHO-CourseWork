package org.example.servlets;

import org.example.dao.UserDAO;
import org.example.models.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet
{
    private final UserDAO userDAO = new UserDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String fullName = request.getParameter("fullName");
        String city = request.getParameter("city");
        String country = request.getParameter("country");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String birthDateString = request.getParameter("birthDate");
        String email = request.getParameter("email");
        if (fullName == null || city == null || country == null || login == null || password == null || birthDateString == null || email == null ||
                fullName.isEmpty() || city.isEmpty() || country.isEmpty() || login.isEmpty() || password.isEmpty() || birthDateString.isEmpty() || email.isEmpty())
        {
            request.setAttribute("error", "All fields are required.");
            request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
            return;
        }
        Date birthDate;
        try
        {
            birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(birthDateString);
        }
        catch (ParseException e)
        {
            request.setAttribute("error", "Invalid birth date format.");
            request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
            return;
        }
        User user = new User(fullName, city, country, login, password, birthDate, email);
        boolean isRegistered = userDAO.addUser(user);
        if (isRegistered)
        {
            response.sendRedirect("login");
        }
        else
        {
            request.setAttribute("error", "Registration failed. Try again.");
            request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
        }
    }
}
