package org.example.dao;

import org.example.models.User;
import org.example.utils.DatabaseConnection;

import java.sql.*;

public class UserDAO
{
    private static final String INSERT_USER = "INSERT INTO users (full_name, city, country, login, password, birth_date, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_USER_BY_LOGIN = "SELECT * FROM users WHERE login = ?";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    private static final String UPDATE_USER = "UPDATE users SET full_name = ?, city = ?, country = ?, email = ?, rating = ?, is_blocked = ? WHERE id = ?";
    public boolean addUser(User user)
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
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
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
                user = mapResultSetToUser(rs);
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
             PreparedStatement stmt = conn.prepareStatement(SELECT_USER_BY_ID)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
            {
                user = mapResultSetToUser(rs);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return user;
    }
    public User validateUser(String login, String password)
    {
        User user = getUserByLogin(login);
        if (user != null && user.getPassword().equals(password))
        {
            return user;
        }
        return null;
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
    private User mapResultSetToUser(ResultSet rs) throws SQLException
    {
        return new User(
                rs.getString("full_name"),
                rs.getString("city"),
                rs.getString("country"),
                rs.getString("login"),
                rs.getString("password"),
                rs.getDate("birth_date"),
                rs.getString("email")
        );
    }
}
