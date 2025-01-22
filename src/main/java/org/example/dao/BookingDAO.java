package org.example.dao;

import org.example.models.Booking;
import org.example.utils.DatabaseConnection;

import java.sql.*;

public class BookingDAO
{
    private static final String INSERT_BOOKING = "INSERT INTO bookings (tenant_id, property_id, start_date, end_date, status) VALUES (?, ?, ?, ?, 'PENDING')";
    public void addBooking(Booking booking)
    {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_BOOKING))
        {
            stmt.setInt(1, booking.getTenantId());
            stmt.setInt(2, booking.getPropertyId());
            stmt.setDate(3, new java.sql.Date(booking.getStartDate().getTime()));
            stmt.setDate(4, new java.sql.Date(booking.getEndDate().getTime()));
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
