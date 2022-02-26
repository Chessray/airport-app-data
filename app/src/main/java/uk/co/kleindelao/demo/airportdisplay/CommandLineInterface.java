package uk.co.kleindelao.demo.airportdisplay;

import java.time.LocalDate;
import java.util.Scanner;

public class CommandLineInterface {
  private final DataReader dataReader;

  public CommandLineInterface(final DataReader dataReader) {
    this.dataReader = dataReader;
  }

  public void getFlightsForDate() {
    final var scanner = new Scanner(System.in);
    final var year = scanner.nextInt();
    final var month = scanner.nextInt();
    final var dateOfMonth = scanner.nextInt();
    dataReader.getFlightsForDateSortedByTime(LocalDate.of(year, month, dateOfMonth));
  }
}
