package uk.co.kleindelao.demo.airportdisplay;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptySet;
import static org.assertj.core.api.BDDAssertions.then;

import java.time.LocalTime;
import java.util.List;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;

class ResponseTableRendererTest {
  final ResponseTableRenderer underTest = new ResponseTableRenderer();

  @Test
  void shouldRenderTableHeader() {
    final var responseTable = underTest.renderFlights(emptyList());
    final var responseLines = responseTable.lines()
                                           .toList();
    then(responseLines).hasSize(4);
  }

  @Test
  void shouldRenderSingleFlight() {
    final var destination = RandomString.make(11);
    final var destinationCode = RandomString.make(3);
    final var flightNumber = RandomString.make(5);
    final var flights = List.of(new Flight(LocalTime.of(2, 8),
        destination, destinationCode, flightNumber, emptySet()));

    final var responseTable = underTest.renderFlights(flights);

    final var responseLines = responseTable.lines()
                                           .toList();
    then(responseLines).hasSize(5);
    then(responseLines.get(3)).isEqualTo("|           02:08 | " + destination + " |              " +
        "     " + destinationCode + " |         " + flightNumber + " |");
  }
}
