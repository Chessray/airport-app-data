package uk.co.kleindelao.demo.airportdisplay;

import static java.time.DayOfWeek.TUESDAY;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.time.LocalTime;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Test;

class CsvRecordToFlightConverterTest {
  final CsvRecordToFlightConverter underTest = new CsvRecordToFlightConverter();

  @Test
  void shouldConvertInputRecord() {
    final var inputRecord = mock(CSVRecord.class);
    final var departureHour = 9;
    final var departureMinute = 0;
    final var destination = "Antigua";
    final var destinationCode = "ANU";
    final var flightNumber = "VS033";
    given(inputRecord.get("Departure Time")).willReturn(String.format("%02d:%02d", departureHour,
        departureMinute));
    given(inputRecord.get("Destination")).willReturn(destination);
    given(inputRecord.get("Destination Airport IATA")).willReturn(destinationCode);
    given(inputRecord.get("Flight No")).willReturn(flightNumber);
    given(inputRecord.get("Sunday")).willReturn("");
    given(inputRecord.get("Monday")).willReturn("");
    given(inputRecord.get("Tuesday")).willReturn("x");
    given(inputRecord.get("Wednesday")).willReturn("");
    given(inputRecord.get("Thursday")).willReturn("");
    given(inputRecord.get("Friday")).willReturn("");
    given(inputRecord.get("Saturday")).willReturn("");

    final var flight = underTest.convert(inputRecord);

    then(flight.departureTime()).isEqualTo(LocalTime.of(departureHour, departureMinute, 0));
    then(flight.destination()).isEqualTo(destination);
    then(flight.destinationIataCode()).isEqualTo(destinationCode);
    then(flight.number()).isEqualTo(flightNumber);
    then(flight.days()).containsExactly(TUESDAY);
  }
}