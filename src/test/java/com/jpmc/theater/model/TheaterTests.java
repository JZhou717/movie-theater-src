package com.jpmc.theater.model;

import com.jpmc.theater.model.Customer;
import com.jpmc.theater.model.Reservation;
import com.jpmc.theater.model.Theater;
import com.jpmc.theater.provider.LocalDateProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TheaterTests {
    Theater theater;

    @BeforeEach
    void getTheater() {
        theater = new Theater(LocalDateProvider.singleton());
        theater.setSchedule(theater.generateSampleShowings());
    }

    @Test
    void printMovieSchedulePlainText() {
        theater.printSchedule();
    }

    @Test
    void printMovieScheduleJSONText() {
        theater.printSchedule(true);
    }

    /**
     * Tests reserving tickets at this theater
     */
    @Test
    void reserveTest() {
        var customer = new Customer("John Doe", "123-id");
        assertTrue(theater.reserve(customer, 1, 3).totalFee() == 24);
    }
}
