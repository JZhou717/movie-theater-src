package com.jpmc.theater.model;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShowingTests {


    /**
     * No discount on movie
     */
    @Test
    void normalShowingFee() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 0);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 0)));
        assertEquals(12.5, showing.calculateFee());
    }

    /**
     * 20% Special discount on movie
     */
    @Test
    void specialShowingDiscount() {
        // 20
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 1);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 0)));
        assertEquals(10, showing.calculateFee());
    }

    /**
     * $3 discount on first showing
     */
    @Test
    void firstShowingDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 0);
        Showing showing = new Showing(spiderMan, 1, LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 0)));
        assertEquals(9.5, showing.calculateFee());
    }

    /**
     * 25% time discount on 12pm showing
     */
    @Test
    void timeDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 0);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 0)));
        // rounding error using power of 10 rounding
        assertEquals(12.5 - (Math.round(12.5*.25 * 100.0) / 100.0), showing.calculateFee());
    }

    /**
     * 25% aka %25 discount on special movie with first showing
     */
    @Test
    void greatestDiscountTest() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),100, 1);
        Showing showing = new Showing(spiderMan, 1, LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 0)));
        assertEquals(75, showing.calculateFee());
    }
}
