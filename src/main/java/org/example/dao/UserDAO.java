package org.example.dao;

import org.example.models.User;
import org.example.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO
{
    private static final String INSERT_USER = "INSERT INTO users (full_name, city, country, login, password, birth_date, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_USER_BY_LOGIN = "SELECT * FROM users WHERE login = ?";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    private static final String UPDATE_USER = "UPDATE users SET full_name = ?, city = ?, country = ?, email = ?, rating = ?, is_blocked = ? WHERE id = ?";
    public void addUser(User user)
    {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_USER))
        {
            stmt.setString(1, user.getFullName());
            stmt.setString(2, user.getCity());
            stmt.setString(3, user.getCountry());
            stmt.setString(4, user.getLogin());
            stmt.setString(5, user.getPassword());
            stmt.setDate(6, new java.sql.Date(user.getBirthDate().getTime()));
            stmt.setString(7, user.getEmail());
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public User getUserByLogin(String login)
    {
        User user = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_USER_BY_LOGIN))
        {
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
            {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setFullName(rs.getString("full_name"));
                user.setCity(rs.getString("city"));
                user.setCountry(rs.getString("country"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setBirthDate(rs.getDate("birth_date"));
                user.setEmail(rs.getString("email"));
                user.setRating(rs.getFloat("rating"));
                user.setBlocked(rs.getBoolean("is_blocked"));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return user;
    }
    public User getUserById(int id)
    {
        User user = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_USER_BY_ID))
        {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
            {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setFullName(rs.getString("full_name"));
                user.setCity(rs.getString("city"));
                user.setCountry(rs.getString("country"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setBirthDate(rs.getDate("birth_date"));
                user.setEmail(rs.getString("email"));
                user.setRating(rs.getFloat("rating"));
                user.setBlocked(rs.getBoolean("is_blocked"));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return user;
    }
    public void updateUser(User user)
    {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_USER))
        {
            stmt.setString(1, user.getFullName());
            stmt.setString(2, user.getCity());
            stmt.setString(3, user.getCountry());
            stmt.setString(4, user.getEmail());
            stmt.setFloat(5, user.getRating());
            stmt.setBoolean(6, user.isBlocked());
            stmt.setInt(7, user.getId());
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
