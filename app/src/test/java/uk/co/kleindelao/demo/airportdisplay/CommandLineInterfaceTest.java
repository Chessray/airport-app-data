package uk.co.kleindelao.demo.airportdisplay;

import static com.github.stefanbirkner.systemlambda.SystemLambda.withTextFromSystemIn;

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
    withTextFromSystemIn(String.valueOf(year), String.valueOf(month), String.valueOf(dateOfMonth))
        .execute(() -> commandLineInterface.getFlightsForDate());

    BDDMockito.then(dataReader)
              .should()
              .getFlightsForDateSortedByTime(LocalDate.of(year, month,
                  dateOfMonth));
  }
}
