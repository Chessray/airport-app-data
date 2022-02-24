package uk.co.kleindelao.demo.airportdisplay;

import static java.time.format.DateTimeFormatter.ISO_TIME;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toUnmodifiableMap;
import static java.util.stream.Collectors.toUnmodifiableSet;
import static org.apache.commons.lang3.StringUtils.capitalize;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.apache.commons.lang3.StringUtils.trim;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.csv.CSVRecord;

public class CsvRecordToFlightConverter {
  private static final Map<String, DayOfWeek> RECORD_HEADERS_2_WEEKDAYS =
      Arrays.stream(DayOfWeek.values())
            .collect(
                toUnmodifiableMap(dayOfWeek -> capitalize(dayOfWeek.name()
                                                                   .toLowerCase()), identity()));

  public Flight convert(final CSVRecord record) {
    final var departureTime = LocalTime.parse(trim(record.get("Departure Time")), ISO_TIME);
    final var destination = record.get("Destination");
    final var destinationCode = record.get("Destination Airport IATA");
    final var flightNumber = record.get("Flight No");
    final var weekdays = RECORD_HEADERS_2_WEEKDAYS.entrySet()
                                                  .stream()
                                                  .filter(recordHeaderEntry -> isNotEmpty(
                                                      record.get(recordHeaderEntry.getKey())))
                                                  .map(Entry::getValue)
                                                  .collect(toUnmodifiableSet());

    return new Flight(departureTime, destination, destinationCode, flightNumber, weekdays);
  }
}
