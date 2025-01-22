package org.example.controllers;

import org.example.models.Rating;
import org.example.services.RatingService;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class RatingController extends HttpServlet
{
    private RatingService ratingService;
    @Override
    public void init() throws ServletException
    {
        ratingService = new RatingService();
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String requestBody = request.getReader().readLine();
        Gson gson = new Gson();
        Rating rating = gson.fromJson(requestBody, Rating.class);
        try
        {
            ratingService.addRating(rating.getReviewerId(), rating.getReviewedId(), rating.getRating(), rating.getReviewText());
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("{\"message\": \"Рейтинг успішно додано!\"}");
        }
        catch (Exception e)
        {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"Помилка при додаванні рейтингу!\"}");
        }
    }
}