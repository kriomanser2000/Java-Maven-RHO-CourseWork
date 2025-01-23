package org.example.controllers;

import org.example.models.Property;
import org.example.services.PropertyService;
import com.google.gson.Gson;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class PropertyController extends HttpServlet
{
    private PropertyService propertyService;
    @Override
    public void init() throws ServletException
    {
        propertyService = new PropertyService();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String requestBody = request.getReader().readLine();
        Gson gson = new Gson();
        Property property = gson.fromJson(requestBody, Property.class);
        try
        {
            propertyService.addProperty(property);
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("{\"message\": \"Житло успішно додано!\"}");
        }
        catch (Exception e)
        {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"Помилка при додаванні житла!\"}");
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String city = request.getParameter("city");
        String country = request.getParameter("country");
        String startDate = request.getParameter("start_date");
        String endDate = request.getParameter("end_date");
        String maxPrice = request.getParameter("max_price");

        try
        {
            Double maxPriceValue = (maxPrice != null && !maxPrice.isEmpty()) ? Double.valueOf(maxPrice) : null;
            List<Property> properties = propertyService.searchProperties(city, country, startDate, endDate, maxPriceValue);
            Gson gson = new Gson();
            response.getWriter().write(gson.toJson(properties));
            response.setStatus(HttpServletResponse.SC_OK);
        }
        catch (Exception e)
        {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"Помилка при пошуку житла!\"}");
        }
    }
}
