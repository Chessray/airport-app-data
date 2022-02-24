package uk.co.kleindelao.demo.airportdisplay;

import static java.time.DayOfWeek.TUESDAY;

import java.time.LocalTime;
import java.util.EnumSet;

public class DataReader {
  public int getNumberOfEntries() {
    return 28;
  }

  public Flight getFirstEntry() {
    return new Flight(LocalTime.of(9, 0, 0), "Antigua", "ANU", "VS033", EnumSet.of(TUESDAY));
  }
}
