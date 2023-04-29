package com.jpmc.theater.model;

import com.jpmc.theater.model.Customer;
import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Reservation;
import com.jpmc.theater.model.Showing;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReservationTests {

    /**
     * Tests calculating fee for movie with no discounts
     */
    @Test
    void totalFee() {
        var customer = new Customer("John Doe", "unused-id");
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 0),
                4,
                LocalDateTime.of(LocalDate.now(), LocalTime.of(10,0))
        );

        assertTrue(new Reservation(customer, showing, 3).totalFee() == 37.5);
    }
}
