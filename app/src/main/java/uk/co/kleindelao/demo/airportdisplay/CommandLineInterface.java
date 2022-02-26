package uk.co.kleindelao.demo.airportdisplay;

import java.time.LocalDate;
import java.util.Scanner;

public class CommandLineInterface {
  private final DataReader dataReader;

  public CommandLineInterface(final DataReader dataReader) {
    this.dataReader = dataReader;
  }

  public void getFlightsForDate() {
    dataReader.getFlightsForDateSortedByTime(getFlightDate());
  }

  private LocalDate getFlightDate() {
    final var scanner = new Scanner(System.in);
    System.out.println("Please enter year:");
    final var year = scanner.nextInt();
    System.out.println("Please enter month:");
    final var month = scanner.nextInt();
    System.out.println("Please enter date of month:");
    final var dateOfMonth = scanner.nextInt();
    return LocalDate.of(year, month, dateOfMonth);
  }
}
