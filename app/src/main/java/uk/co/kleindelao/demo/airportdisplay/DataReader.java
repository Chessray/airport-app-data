package uk.co.kleindelao.demo.airportdisplay;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Comparator.comparing;
import static org.apache.commons.csv.CSVFormat.DEFAULT;
import static org.apache.commons.csv.CSVParser.parse;

import com.google.common.annotations.VisibleForTesting;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import org.apache.commons.csv.CSVFormat;

public class DataReader {
  private final List<Flight> flights;

  public DataReader() throws IOException {
    final var converter = new CsvRecordToFlightConverter();
    flights = parse(DataReader.class.getResource("/flights.csv"), UTF_8,
        CSVFormat.Builder.create(DEFAULT)
                         .setHeader()
                         .setSkipHeaderRecord(true)
                         .build())
        .stream()
        .map(converter::convert)
        .sorted(comparing(Flight::departureTime))
        .toList();
  }

  @VisibleForTesting
  int getNumberOfEntries() {
    return flights.size();
  }

  public List<Flight> getFlightsForDateSortedByTime(final LocalDate date) {
    final var dayOfWeek = date.getDayOfWeek();
    return flights.stream()
                  .filter(flight -> flight.fliesOnDayOfWeek(dayOfWeek))
                  .toList();
  }
}
