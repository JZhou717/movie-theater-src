package com.jpmc.theater.provider;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class LocalDateProvider {
    private static LocalDateProvider instance = null;

    public static LocalDateProvider singleton() {
        if (instance == null) {
            instance = new LocalDateProvider();
        }
            return instance;
        }

    public LocalDate currentDate() {
            return LocalDate.now();
    }

    public LocalDateTime getLocalDateTime(int hour, int minute) {
        return LocalDateTime.of(currentDate(), LocalTime.of(hour, minute));
    }
}
