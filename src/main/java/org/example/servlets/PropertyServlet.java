package org.example.servlets;

import org.example.dao.PropertyDAO;
import org.example.models.Property;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        List<Property> properties = propertyDAO.searchProperties(city, country, null, null);
        request.setAttribute("properties", properties);
        request.getRequestDispatcher("/WEB-INF/views/properties.jsp").forward(request, response);
    }
}
