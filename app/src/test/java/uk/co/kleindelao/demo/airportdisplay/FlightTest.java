package uk.co.kleindelao.demo.airportdisplay;

import static java.time.DayOfWeek.MONDAY;
import static org.assertj.core.api.BDDAssertions.then;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Set;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;

class FlightTest {
  @Test
  void shouldReturnTrueIffIncludedInDays() {
    final var flight =
        new Flight(LocalTime.now(), RandomString.make(), RandomString.make(), RandomString.make(),
            Set.of(MONDAY));
    then(flight.fliesOnDayOfWeek(MONDAY)).isTrue();
    Arrays.stream(DayOfWeek.values())
          .filter(day -> MONDAY != day)
          .forEach(day -> then(flight.fliesOnDayOfWeek(day)).isFalse());
  }
}