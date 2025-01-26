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
             PreparedStatement stmt = conn.prepareStatement(INSERT_PROPERTY)) {
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
    public void addTestProperties()
    {
        addProperty(new Property(1, "Kyiv", "Ukraine", 500, new java.util.Date(), new java.util.Date()));
        addProperty(new Property(2, "Lviv", "Ukraine", 300, new java.util.Date(), new java.util.Date()));
        addProperty(new Property(3, "Warsaw", "Poland", 700, new java.util.Date(), new java.util.Date()));
    }
    public List<Property> getAllProperties()
    {
        List<Property> properties = new ArrayList<>();
        String query = "SELECT * FROM properties";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery())
        {
            while (resultSet.next())
            {
                Property property = new Property();
                property.setId(resultSet.getInt("id"));
                property.setOwnerId(resultSet.getInt("owner_id"));
                property.setCity(resultSet.getString("city"));
                property.setCountry(resultSet.getString("country"));
                property.setPrice(resultSet.getDouble("price"));
                property.setAvailableFrom(resultSet.getDate("available_from"));
                property.setAvailableTo(resultSet.getDate("available_to"));
                properties.add(property);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return properties;
    }
    public List<String> getAllCountries()
    {
        List<String> countries = new ArrayList<>();
        String query = "SELECT DISTINCT country FROM properties ORDER BY country";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery())
        {
            while (rs.next())
            {
                countries.add(rs.getString("country"));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return countries;
    }
    public List<String> getAllCities()
    {
        List<String> cities = new ArrayList<>();
        String query = "SELECT DISTINCT city FROM properties ORDER BY city";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery())
        {
            while (rs.next())
            {
                cities.add(rs.getString("city"));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return cities;
    }
    public List<Property> getAllPropertiesSortedByRating()
    {
        List<Property> properties = new ArrayList<>();
        String query = "SELECT p.id, p.city, p.country, p.price, p.available_from, p.available_to, " +
                "u.full_name AS owner_name, u.rating AS owner_rating " +
                "FROM properties p " +
                "JOIN users u ON p.owner_id = u.id " +
                "ORDER BY u.rating DESC";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery())
        {
            while (resultSet.next())
            {
                Property property = new Property();
                property.setId(resultSet.getInt("id"));
                property.setCity(resultSet.getString("city"));
                property.setCountry(resultSet.getString("country"));
                property.setPrice(resultSet.getDouble("price"));
                property.setAvailableFrom(resultSet.getDate("available_from"));
                property.setAvailableTo(resultSet.getDate("available_to"));
                property.setOwnerName(resultSet.getString("owner_name"));
                property.setOwnerRating(resultSet.getDouble("owner_rating"));
                properties.add(property);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return properties;
    }
    public List<Property> searchProperties(String city, String country, Double maxPrice, java.util.Date startDate, java.util.Date endDate)
    {
        List<Property> properties = new ArrayList<>();
        if (city == null || country == null)
        {
            return properties;
        }
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SEARCH_PROPERTIES))
        {
            stmt.setString(1, city);
            stmt.setString(2, country);
            if (maxPrice != null)
            {
                stmt.setDouble(3, maxPrice);
            }
            else
            {
                stmt.setNull(3, Types.DOUBLE);
            }
            if (startDate != null)
            {
                stmt.setDate(4, new java.sql.Date(startDate.getTime()));
            }
            else
            {
                stmt.setNull(4, Types.DATE);
            }
            if (endDate != null)
            {
                stmt.setDate(5, new java.sql.Date(endDate.getTime()));
            }
            else
            {
                stmt.setNull(5, Types.DATE);
            }
            try (ResultSet rs = stmt.executeQuery())
            {
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
            try (ResultSet rs = stmt.executeQuery())
            {
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
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return Optional.ofNullable(property);
    }
}
