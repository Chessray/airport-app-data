package uk.co.kleindelao.demo.airportdisplay;

import static java.time.DayOfWeek.TUESDAY;
import static org.assertj.core.api.BDDAssertions.then;

import java.time.LocalTime;
import org.junit.jupiter.api.Test;

public class DataReaderTest {
  private final DataReader underTest = new DataReader();

  @Test
  void shouldReturnNumberOfRecords() {
    final var numberOfEntries = underTest.getNumberOfEntries();

    then(numberOfEntries).isEqualTo(28);
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
}
