package uk.co.kleindelao.demo.airportdisplay;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.IOException;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

public class DataReader {
  private final List<Flight> flights;

  public DataReader() throws IOException {
    final var converter = new CsvRecordToFlightConverter();
    flights = CSVParser.parse(DataReader.class.getResource("/flights.csv"), UTF_8,
                           CSVFormat.Builder.create(CSVFormat.DEFAULT)
                                            .setHeader()
                                            .setSkipHeaderRecord(true)
                                            .build())
                       .stream()
                       .map(converter::convert)
                       .toList();
  }

  public int getNumberOfEntries() {
    return flights.size();
  }

  public Flight getFirstEntry() {
    return flights.iterator().next();
  }
}
