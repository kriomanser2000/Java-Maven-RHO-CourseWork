package org.example.services;

import org.example.dao.RatingDAO;
import org.example.dao.UserDAO;
import org.example.models.Rating;
import org.example.models.User;

public class RatingService
{
    private RatingDAO ratingDAO;
    private UserDAO userDAO;
    public RatingService()
    {
        this.ratingDAO = new RatingDAO();
        this.userDAO = new UserDAO();
    }
    public void addRating(int reviewerId, int reviewedId, int ratingValue, String reviewText)
    {
        if (ratingValue < 1 || ratingValue > 5)
        {
            throw new RuntimeException("Невірний рейтинг! Використовуйте зірки від 1 до 5.");
        }
        Rating rating = new Rating();
        rating.setReviewerId(reviewerId);
        rating.setReviewedId(reviewedId);
        rating.setRating(ratingValue);
        rating.setReviewText(reviewText);
        ratingDAO.addRating(rating);
        User reviewedUser = userDAO.getUserById(reviewedId);
        updateUserRating(reviewedUser);
    }
    private void updateUserRating(User user)
    {
        if (user.getRating() < 2)
        {
            user.setBlocked(true);
            userDAO.updateUser(user);
        }
    }
}
