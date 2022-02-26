package uk.co.kleindelao.demo.airportdisplay;

import static org.assertj.core.api.BDDAssertions.then;

import java.io.IOException;
import java.time.LocalDate;
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
  void shouldReturnMatchingFlightsSortedByTime() {
    final var testDate = LocalDate.of(2022, 2, 25);

    final var flights = underTest.getFlightsForDateSortedByTime(testDate);

    then(flights).isNotNull()
                 .hasSize(7)
                 .extracting(Flight::number)
                 .containsExactly("VS089", "VS089", "VS043", "VS029", "VS027", "VS015", "VS044");
  }
}
