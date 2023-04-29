package com.jpmc.theater.model;

import com.jpmc.theater.util.DiscountUtil;
import com.jpmc.theater.util.PrintUtil;

import java.time.LocalDateTime;
import java.util.Objects;

public class Showing {
    private Movie movie;
    private int sequenceOfTheDay;
    private LocalDateTime showStartTime;

    public Showing(Movie movie, int sequenceOfTheDay, LocalDateTime showStartTime) {
        this.movie = movie;
        this.sequenceOfTheDay = sequenceOfTheDay;
        this.showStartTime = showStartTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public LocalDateTime getStartTime() {
        return showStartTime;
    }

    public boolean isSequence(int sequence) {
        return this.sequenceOfTheDay == sequence;
    }

    public double getMovieFee() {
        return movie.getTicketPrice();
    }

    public int getSequenceOfTheDay() {
        return sequenceOfTheDay;
    }

    /**
     * Calculate showing price after discounts for one audience member
     * @return
     */
    public double calculateFee() {
        return movie.calculateTicketPrice(DiscountUtil.getDiscount(this));
    }

    /**
     * Calculate showing price after discounts for given number of audience members
     * @param audienceCount
     * @return
     */
    public double calculateFee(int audienceCount) {
        return movie.calculateTicketPrice(DiscountUtil.getDiscount(this)) * audienceCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Showing)) return false;
        Showing showing = (Showing) o;
        return Objects.equals(movie, showing.movie) && Objects.equals(sequenceOfTheDay, showing.sequenceOfTheDay) && Objects.equals(showStartTime, showing.showStartTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movie, sequenceOfTheDay, showStartTime);
    }

    /**
     * Returns showing as simple text
     * @return
     */
    public String toPrintString() {
        return sequenceOfTheDay +
                ": " +
                showStartTime +
                " " +
                getMovie().getTitle() +
                " " +
                PrintUtil.humanReadableFormat(getMovie().getRunningTime()) +
                " $" +
                calculateFee();
    }

}
