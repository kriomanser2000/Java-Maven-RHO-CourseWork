package org.example.services;

import org.example.utils.DatabaseConnection;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.example.dao.PropertyDAO;
import org.example.models.Property;

import java.util.List;

public class PropertyService
{
    private PropertyDAO propertyDAO;
    public void addProperty(Property property)
    {
        propertyDAO.addProperty(property);
    }
    public PropertyService()
    {
        this.propertyDAO = new PropertyDAO();
    }
    public List<String> getAllCountries()
    {
        return propertyDAO.getAllCountries();
    }
    public List<String> getAllCities()
    {
        return propertyDAO.getAllCities();
    }
    public List<String> getAvailableCountries() {
        List<String> countries = new ArrayList<>();
        String query = "SELECT DISTINCT country FROM properties ORDER BY country";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                countries.add(rs.getString("country"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countries;
    }

    // Отримання унікальних міст по країні
    public List<String> getAvailableCities(String country) {
        List<String> cities = new ArrayList<>();
        String query = "SELECT DISTINCT city FROM properties WHERE country = ? ORDER BY city";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, country);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    cities.add(rs.getString("city"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }
    public List<Property> searchProperties(String city, String country, String startDate, String endDate, Double maxPrice)
    {
        java.util.Date start = (startDate != null) ? java.sql.Date.valueOf(startDate) : null;
        java.util.Date end = (endDate != null) ? java.sql.Date.valueOf(endDate) : null;
        return propertyDAO.searchProperties(city, country, maxPrice, start, end);
    }
    public List<Property> getAllProperties()
    {
        return propertyDAO.getAllProperties();
    }
    public List<Property> getAllPropertiesSortedByRating()
    {
        return propertyDAO.getAllPropertiesSortedByRating();
    }
}
