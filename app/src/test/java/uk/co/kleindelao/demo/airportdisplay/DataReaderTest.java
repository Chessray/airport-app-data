package uk.co.kleindelao.demo.airportdisplay;

import static java.time.DayOfWeek.TUESDAY;
import static org.assertj.core.api.BDDAssertions.then;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.jupiter.api.Test;

public class DataReaderTest {
  private final DataReader underTest = new DataReader();

  public DataReaderTest() throws IOException {
  }

  @Test
  void shouldReturnNumberOfRecords() {
    final var numberOfEntries = underTest.getNumberOfEntries();

    then(numberOfEntries).isEqualTo(27);
  }

  @Test
  void shouldReadFirstRecord() {
    final var firstFlight = underTest.getFirstEntry();

    then(firstFlight.departureTime()).isEqualTo(LocalTime.of(9, 0, 0));
    then(firstFlight.destination()).isEqualTo("Antigua");
    then(firstFlight.destinationIataCode()).isEqualTo("ANU");
    then(firstFlight.number()).isEqualTo("VS033");
    then(firstFlight.days()).containsExactly(TUESDAY);
  }

  @Test
  void shouldReturnMatchingFlightsSortedByTime() {
    final var testDate = LocalDate.of(2022, 2, 25);

    final var flights = underTest.getFlightsForDateSortedByTime(testDate);

    then(flights).isNotNull()
                 .hasSize(7)
                 .extracting(Flight::number)
                 .containsExactly("VS089", "VS089", "VS043", "VS029", "VS027", "VS015", "VS044");
  }
}
