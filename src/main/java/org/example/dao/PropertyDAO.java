package org.example.dao;

import org.example.models.Property;
import org.example.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PropertyDAO
{
    private static final String INSERT_PROPERTY = "INSERT INTO properties (owner_id, city, country, price, available_from, available_to) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_PROPERTY_BY_ID = "SELECT * FROM properties WHERE id = ?";
    private static final String SEARCH_PROPERTIES = "SELECT * FROM properties WHERE city = ? AND country = ? AND price <= ? AND available_from <= ? AND available_to >= ?";
    public void addProperty(Property property) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_PROPERTY))
        {
            stmt.setInt(1, property.getOwnerId());
            stmt.setString(2, property.getCity());
            stmt.setString(3, property.getCountry());
            stmt.setDouble(4, property.getPrice());
            stmt.setDate(5, new java.sql.Date(property.getAvailableFrom().getTime()));
            stmt.setDate(6, new java.sql.Date(property.getAvailableTo().getTime()));
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public List<Property> searchProperties(String city, String country, java.util.Date startDate, java.util.Date endDate)
    {
        List<Property> properties = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SEARCH_PROPERTIES))
        {
            stmt.setString(1, city);
            stmt.setString(2, country);
            stmt.setDouble(3, 1000000);
            stmt.setDate(4, new java.sql.Date(startDate.getTime()));
            stmt.setDate(5, new java.sql.Date(endDate.getTime()));
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                Property property = new Property();
                property.setId(rs.getInt("id"));
                property.setOwnerId(rs.getInt("owner_id"));
                property.setCity(rs.getString("city"));
                property.setCountry(rs.getString("country"));
                property.setPrice(rs.getDouble("price"));
                property.setAvailableFrom(rs.getDate("available_from"));
                property.setAvailableTo(rs.getDate("available_to"));
                properties.add(property);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return properties;
    }
}
