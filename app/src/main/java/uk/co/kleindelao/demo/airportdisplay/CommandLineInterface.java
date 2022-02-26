package uk.co.kleindelao.demo.airportdisplay;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class CommandLineInterface {
  private final DataReader dataReader;
  private final ResponseTableRenderer responseTableRenderer;

  public CommandLineInterface(final DataReader dataReader,
                              final ResponseTableRenderer responseTableRenderer) {
    this.dataReader = dataReader;
    this.responseTableRenderer = responseTableRenderer;
  }

  public void getFlightsForDate() {
    System.out.println(responseTableRenderer.renderFlights(
        dataReader.getFlightsForDateSortedByTime(getFlightDate())));
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

  public static void main(String[] args) throws IOException {
    new CommandLineInterface(new DataReader(), new ResponseTableRenderer()).getFlightsForDate();
  }
}
