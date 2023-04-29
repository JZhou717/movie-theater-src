package com.jpmc.theater.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jpmc.theater.provider.LocalDateProvider;

import java.time.Duration;
import java.util.List;

public class Theater {

    LocalDateProvider provider;
    private List<Showing> schedule;

    public Theater(LocalDateProvider provider) {
        this.provider = provider;
        schedule = generateSampleShowings();
    }

    public Theater(LocalDateProvider provider, List<Showing> schedule) {
        this.provider = provider;
        this.schedule = schedule;
    }

    /**
     * Generates a sample list of showings for this theater
     * @return
     */
    public List<Showing> generateSampleShowings() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11, 0);
        Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 9, 0);

        return List.of(
                new Showing(turningRed, 1, provider.getLocalDateTime(9, 0)),
                new Showing(spiderMan, 2, provider.getLocalDateTime(11, 0)),
                new Showing(theBatMan, 3, provider.getLocalDateTime(12, 50)),
                new Showing(turningRed, 4, provider.getLocalDateTime(14, 30)),
                new Showing(spiderMan, 5, provider.getLocalDateTime(16, 10)),
                new Showing(theBatMan, 6, provider.getLocalDateTime(17, 50)),
                new Showing(turningRed, 7, provider.getLocalDateTime(19, 30)),
                new Showing(spiderMan, 8, provider.getLocalDateTime(21, 10)),
                new Showing(theBatMan, 9, provider.getLocalDateTime(23, 0))
        );
    }

    public void setSchedule(List<Showing> schedule) {
        this.schedule = schedule;
    }


    /**
     * Reserve tickets for the customer for a showing identified by sequence
     * @param customer
     * @param sequence
     * @param howManyTickets
     * @return Returns the reserveration if we were able to create it. Otherwise, report unable to make reservation to user and returns null
     */
    public Reservation reserve(Customer customer, int sequence, int howManyTickets) {
        Showing showing;
        try {
            showing = schedule.get(sequence - 1);
            return new Reservation(customer, showing, howManyTickets);
        } catch (RuntimeException ex) {
            System.out.println("Could not make reservation. Unable to find any showing for given sequence " + sequence);
        }
        return null;
    }

    /**
     * Default plain text print of the theater schedule
     */
    public void printSchedule(){
        printSchedule(false);
    }

    /**
     * Prints the theater schedule in json format or in plain text
     * @param inJson flag for json format if true
     */
    public void printSchedule(boolean inJson){
        if(inJson) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.registerModule(new JavaTimeModule());
                System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(schedule));
            } catch (JsonProcessingException e) {
                printSchedule(false);
            }
        } else {
            System.out.println(provider.currentDate());
            System.out.println("===================================================");
            schedule.forEach(s ->
                    System.out.println(s.toPrintString())
            );
            System.out.println("===================================================");
        }
    }

    public static void main(String[] args){
        Theater theater = new Theater(LocalDateProvider.singleton());
        theater.printSchedule();
        theater.printSchedule(true);
    }
}
