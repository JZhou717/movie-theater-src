package com.jpmc.theater.util;

import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Showing;

import java.time.LocalDateTime;

public class DiscountUtil {

    /**
     * Calculates discount for the giving showing
     * Discounts are based on 3 categories: special movies, sequence of showing, and time of day of start time
     * The largest single discount is returned
     * @param showing The movie showing which we are calculating a discount for
     * @return
     */
    public static double getDiscount(Showing showing) {
        Movie movie = showing.getMovie();

        double specialDiscount = 0;
        if(movie.isSpecialMovie()) {
            specialDiscount = movie.getTicketPrice() * 0.2;  // 20% discount for special movie
        }

        double sequenceDiscount = 0;
        if (showing.getSequenceOfTheDay() == 1) {
            sequenceDiscount = 3; // $3 discount for 1st show
        } else if (showing.getSequenceOfTheDay() == 2) {
            sequenceDiscount = 2; // $2 discount for 2nd show
        } else if(showing.getSequenceOfTheDay() == 7) {
            sequenceDiscount = 1; // $1 discount for 7th show
        }

        double timeDiscount = 0;
        if(DiscountUtil.between11and4(showing.getStartTime())) {
            timeDiscount = movie.getTicketPrice() * .25; // 25% discount for movies between 11 and 4
        }
        // biggest discount wins
        double maxDiscount = Math.max(specialDiscount, Math.max(sequenceDiscount, timeDiscount));
        // return discount rounded to hundreds:
        return Math.round(maxDiscount * 100.0) / 100.0;
    }

    private static boolean between11and4(LocalDateTime startTime) {
        return startTime.getHour() >= 11 && startTime.getHour() <= 16;
    }
}
