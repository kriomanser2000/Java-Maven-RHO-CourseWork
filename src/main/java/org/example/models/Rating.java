package org.example.models;

public class Rating
{
    private int id;
    private int reviewerId;
    private int reviewedId;
    private int rating;
    private String reviewText;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getReviewerId() {
        return reviewerId;
    }
    public void setReviewerId(int reviewerId) {
        this.reviewerId = reviewerId;
    }
    public int getReviewedId() {
        return reviewedId;
    }
    public void setReviewedId(int reviewedId) {
        this.reviewedId = reviewedId;
    }
    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
    public String getReviewText() {
        return reviewText;
    }
    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }
}
