package uk.co.kleindelao.demo.airportdisplay;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Set;

public record Flight(LocalTime departureTime, String destination, String destinationIataCode,
                     String number, Set<DayOfWeek> days) {
}
