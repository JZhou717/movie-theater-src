package com.jpmc.theater.model;

public class Reservation {
    private Customer customer;
    private Showing showing;
    private int audienceCount;

    public Reservation(Customer customer, Showing showing, int audienceCount) {
        this.customer = customer;
        this.showing = showing;
        this.audienceCount = audienceCount;
    }

    /**
     * Return fee for all tickets for this showing after discounts
     * @return
     */
    public double totalFee() {
        return showing.calculateFee(audienceCount);
    }
}