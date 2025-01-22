package org.example.controllers;

import org.example.models.Booking;
import org.example.services.BookingService;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class BookingController extends HttpServlet
{
    private BookingService bookingService;
    @Override
    public void init() throws ServletException
    {
        bookingService = new BookingService();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String requestBody = request.getReader().readLine();
        Gson gson = new Gson();
        Booking booking = gson.fromJson(requestBody, Booking.class);
        try
        {
            bookingService.createBooking(booking.getTenantId(), booking.getPropertyId(), booking.getStartDate(), booking.getEndDate());
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("{\"message\": \"Бронювання створено!\"}");
        }
        catch (Exception e)
        {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"Помилка при створенні бронювання!\"}");
        }
    }
}
