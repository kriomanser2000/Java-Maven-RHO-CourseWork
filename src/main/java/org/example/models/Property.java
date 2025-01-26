package org.example.models;

import java.util.Date;

public class Property
{
    private int id;
    private int ownerId;
    private String city;
    private String country;
    private double price;
    private Date availableFrom;
    private Date availableTo;
    private Double rating;
    public Property() {}
    public Property(int ownerId, String city, String country, double price, Date availableFrom, Date availableTo)
    {
        this.ownerId = ownerId;
        this.city = city;
        this.country = country;
        this.price = price;
        this.availableFrom = availableFrom;
        this.availableTo = availableTo;
    }
    public Property(String city, String country, double price)
    {
        this.city = city;
        this.country = country;
        this.price = price;
    }
    private String ownerName;
    private double ownerRating;
    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }
    public double getOwnerRating() { return ownerRating; }
    public void setOwnerRating(double ownerRating) { this.ownerRating = ownerRating; }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getOwnerId() { return ownerId; }
    public void setOwnerId(int ownerId) { this.ownerId = ownerId; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public Date getAvailableFrom() { return availableFrom; }
    public void setAvailableFrom(Date availableFrom) { this.availableFrom = availableFrom; }
    public Date getAvailableTo() { return availableTo; }
    public void setAvailableTo(Date availableTo) { this.availableTo = availableTo; }
    public Object getRating() { return (rating != null) ? rating : "Немає рейтингу"; }
    public void setRating(Double rating) { this.rating = rating; }
}
