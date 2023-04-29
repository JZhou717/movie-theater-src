package com.jpmc.theater.provider;

import com.jpmc.theater.provider.LocalDateProvider;
import org.junit.jupiter.api.Test;

public class LocalDateProviderTests {
    @Test
    void makeSureCurrentTime() {
        System.out.println("current time is - " + LocalDateProvider.singleton().currentDate());
    }
}
