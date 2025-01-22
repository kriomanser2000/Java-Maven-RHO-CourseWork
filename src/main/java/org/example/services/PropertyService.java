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
    public List<Property> searchProperties(String city, String country, String startDate, String endDate)
    {
        java.util.Date start = java.sql.Date.valueOf(startDate);
        java.util.Date end = java.sql.Date.valueOf(endDate);
        return propertyDAO.searchProperties(city, country, start, end);
    }
}
