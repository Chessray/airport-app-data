package uk.co.kleindelao.demo.airportdisplay;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static com.github.stefanbirkner.systemlambda.SystemLambda.withTextFromSystemIn;
import static org.assertj.core.api.BDDAssertions.then;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CommandLineInterfaceTest {
  @Mock
  private DataReader dataReader;

  @InjectMocks
  private CommandLineInterface commandLineInterface;

  @Test
  void shouldParseCommandLineInformationIntoDataReaderQuery() throws Exception {
    final var year = 2022;
    final var month = 2;
    final var dateOfMonth = 25;
    final var output = tapSystemOut(
        () -> withTextFromSystemIn(String.valueOf(year), String.valueOf(month),
            String.valueOf(dateOfMonth)).execute(() -> commandLineInterface.getFlightsForDate()));

    BDDMockito.then(dataReader)
              .should()
              .getFlightsForDateSortedByTime(LocalDate.of(year, month,
                  dateOfMonth));
    final var outputLines = output.lines()
                                  .toList();
    then(outputLines).containsExactly("Please enter year:", "Please enter month:",
        "Please enter date of month:");
  }
}
