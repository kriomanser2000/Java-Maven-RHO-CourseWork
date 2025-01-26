package org.example.services;

import org.example.dao.PropertyDAO;
import org.example.models.Property;

import java.util.List;

public class PropertyService
{
    private PropertyDAO propertyDAO;
    public PropertyService()
    {
        this.propertyDAO = new PropertyDAO();
    }
    public void addProperty(Property property)
    {
        propertyDAO.addProperty(property);
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
}
