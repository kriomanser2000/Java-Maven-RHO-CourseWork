package org.example.dao;

import org.example.models.Property;
import org.example.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PropertyDAO
{
    private static final String INSERT_PROPERTY =
            "INSERT INTO properties (owner_id, city, country, price, available_from, available_to) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_PROPERTY_BY_ID =
            "SELECT * FROM properties WHERE id = ?";
    private static final String SEARCH_PROPERTIES =
            "SELECT * FROM properties WHERE city = ? AND country = ? " +
                    "AND (price <= ? OR ? IS NULL) " +
                    "AND (available_from <= ? OR ? IS NULL) " +
                    "AND (available_to >= ? OR ? IS NULL)";
    public void addProperty(Property property)
    {
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
    public List<Property> searchProperties(String city, String country, Double maxPrice, java.util.Date startDate, java.util.Date endDate)
    {
        List<Property> properties = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SEARCH_PROPERTIES))
        {
            stmt.setString(1, city);
            stmt.setString(2, country);
            if (maxPrice != null)
            {
                stmt.setDouble(3, maxPrice);  // Параметр для обмеження ціни
                stmt.setDouble(4, maxPrice);  // Параметр для перевірки ціни на NULL
            }
            else
            {
                stmt.setNull(3, Types.DOUBLE);
                stmt.setNull(4, Types.DOUBLE);
            }
            if (startDate != null)
            {
                stmt.setDate(5, new java.sql.Date(startDate.getTime()));  // Дата початку
                stmt.setDate(6, new java.sql.Date(startDate.getTime()));  // Дата початку (перевірка на NULL)
            }
            else
            {
                stmt.setNull(5, Types.DATE);
                stmt.setNull(6, Types.DATE);
            }
            if (endDate != null)
            {
                stmt.setDate(7, new java.sql.Date(endDate.getTime()));  // Дата закінчення
                stmt.setDate(8, new java.sql.Date(endDate.getTime()));  // Дата закінчення (перевірка на NULL)
            }
            else
            {
                stmt.setNull(7, Types.DATE);
                stmt.setNull(8, Types.DATE);
            }
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
    public Optional<Property> getPropertyById(int id)
    {
        Property property = null;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_PROPERTY_BY_ID))
        {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
            {
                property = new Property(
                        rs.getInt("owner_id"),
                        rs.getString("city"),
                        rs.getString("country"),
                        rs.getDouble("price"),
                        rs.getDate("available_from"),
                        rs.getDate("available_to")
                );
                property.setId(rs.getInt("id"));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return Optional.ofNullable(property);
    }
}
