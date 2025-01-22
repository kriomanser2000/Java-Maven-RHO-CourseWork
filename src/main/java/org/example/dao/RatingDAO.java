package org.example.dao;

import org.example.models.Rating;
import org.example.utils.DatabaseConnection;

import java.sql.*;

public class RatingDAO
{
    private static final String INSERT_RATING = "INSERT INTO ratings (reviewer_id, reviewed_id, rating, review_text) VALUES (?, ?, ?, ?)";
    public void addRating(Rating rating)
    {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_RATING))
        {
            stmt.setInt(1, rating.getReviewerId());
            stmt.setInt(2, rating.getReviewedId());
            stmt.setInt(3, rating.getRating());
            stmt.setString(4, rating.getReviewText());
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
