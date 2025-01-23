package org.example.services;

import org.example.dao.BookingDAO;
import org.example.dao.PropertyDAO;
import org.example.models.Booking;
import org.example.models.Property;

import java.util.Date;

public class BookingService
{
    private BookingDAO bookingDAO;
    private PropertyDAO propertyDAO;
    public BookingService()
    {
        this.bookingDAO = new BookingDAO();
        this.propertyDAO = new PropertyDAO();
    }
    public void createBooking(int tenantId, int propertyId, Date startDate, Date endDate)
    {
        Property property = propertyDAO.getPropertyById(propertyId)
                .orElseThrow(() -> new RuntimeException("Житло не знайдено!"));
        if (startDate.before(property.getAvailableFrom()) || endDate.after(property.getAvailableTo()))
        {
            throw new RuntimeException("Житло не доступне на цей період!");
        }
        Booking booking = new Booking();
        booking.setTenantId(tenantId);
        booking.setPropertyId(propertyId);
        booking.setStartDate(startDate);
        booking.setEndDate(endDate);
        bookingDAO.addBooking(booking);
    }
}
