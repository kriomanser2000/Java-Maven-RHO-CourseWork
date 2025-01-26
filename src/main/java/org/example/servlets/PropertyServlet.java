package org.example.servlets;

import org.example.dao.PropertyDAO;
import org.example.models.Property;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet("/properties")
public class PropertyServlet extends HttpServlet
{
    private PropertyDAO propertyDAO;
    @Override
    public void init()
    {
        propertyDAO = new PropertyDAO();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String city = request.getParameter("city");
        String country = request.getParameter("country");
        List<Property> properties = List.of();
        System.out.println("Properties found: " + properties.size());
        for (Property p : properties)
        {
            System.out.println(p.getCity() + ", " + p.getCountry() + " - " + p.getPrice());
        }
        if (city == null || city.isEmpty() || country == null || country.isEmpty())
        {
            properties = propertyDAO.getAllProperties();
        }
        else
        {
            Double maxPrice = request.getParameter("maxPrice") != null ? Double.parseDouble(request.getParameter("maxPrice")) : null;
            Date startDate = request.getParameter("startDate") != null ? java.sql.Date.valueOf(request.getParameter("startDate")) : null;
            Date endDate = request.getParameter("endDate") != null ? java.sql.Date.valueOf(request.getParameter("endDate")) : null;
            properties = propertyDAO.searchProperties(city, country, maxPrice, startDate, endDate);
        }
        if (properties.isEmpty())
        {
            request.setAttribute("message", "No properties found for the specified city and country.");
        }
        request.setAttribute("properties", properties);
        request.getRequestDispatcher("/WEB-INF/views/properties.jsp").forward(request, response);
    }
}
