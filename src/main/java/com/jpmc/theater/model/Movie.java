package com.jpmc.theater.model;

import java.time.Duration;
import java.util.Objects;

public class Movie {
    private static final int MOVIE_CODE_SPECIAL = 1;

    private String title;
    private String description;
    private Duration runningTime;
    private double ticketPrice;
    private int specialCode;

    /**
     * movie constructor without description
     * @param title
     * @param runningTime
     * @param ticketPrice
     * @param specialCode
     */
    public Movie(String title, Duration runningTime, double ticketPrice, int specialCode) {
        this.title = title;
        this.runningTime = runningTime;
        this.ticketPrice = ticketPrice;
        this.specialCode = specialCode;
    }

    /**
     * movie constructur with description
     * @param title
     * @param description
     * @param runningTime
     * @param ticketPrice
     * @param specialCode
     */
    public Movie(String title, String description, Duration runningTime, double ticketPrice, int specialCode) {
        this.title = title;
        this.description = description;
        this.runningTime = runningTime;
        this.ticketPrice = ticketPrice;
        this.specialCode = specialCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Duration getRunningTime() {
        return runningTime;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    /**
     * return movie ticket price without discount
     * @return
     */
    public double calculateTicketPrice() {
        return ticketPrice;
    }

    /**
     * apply discount to movie ticket price
     * @param discount
     * @return
     */
    public double calculateTicketPrice(double discount) {
        return ticketPrice - discount;
    }

    /**
     * Check if this movie is marked special
     * @return
     */
    public boolean isSpecialMovie() {
        return specialCode == MOVIE_CODE_SPECIAL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Double.compare(movie.ticketPrice, ticketPrice) == 0
                && Objects.equals(title, movie.title)
                && Objects.equals(description, movie.description)
                && Objects.equals(runningTime, movie.runningTime)
                && Objects.equals(specialCode, movie.specialCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, runningTime, ticketPrice, specialCode);
    }
}