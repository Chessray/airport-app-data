package uk.co.kleindelao.demo.airportdisplay;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static com.github.stefanbirkner.systemlambda.SystemLambda.withTextFromSystemIn;
import static java.time.DayOfWeek.WEDNESDAY;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CommandLineInterfaceTest {
  @Mock
  private DataReader dataReader;
  @Mock
  private ResponseTableRenderer responseTableRenderer;

  @InjectMocks
  private CommandLineInterface commandLineInterface;

  @Test
  void shouldParseCommandLineInformationIntoDataReaderQuery() throws Exception {
    final var year = 2022;
    final var month = 2;
    final var dateOfMonth = 25;
    final var flights = List.of(
        new Flight(LocalTime.of(12, 34), RandomString.make(), RandomString.make(),
            RandomString.make(), Set.of(WEDNESDAY)));
    given(dataReader.getFlightsForDateSortedByTime(
        LocalDate.of(year, month, dateOfMonth))).willReturn(flights);
    final String renderedTable = RandomString.make();
    given(responseTableRenderer.renderFlights(flights)).willReturn(renderedTable);

    final var output = tapSystemOut(
        () -> withTextFromSystemIn(String.valueOf(year), String.valueOf(month),
            String.valueOf(dateOfMonth)).execute(() -> commandLineInterface.getFlightsForDate()));

    final var outputLines = output.lines()
                                  .toList();
    then(outputLines).containsExactly("Please enter year:", "Please enter month:",
        "Please enter date of month:", renderedTable);
  }
}
