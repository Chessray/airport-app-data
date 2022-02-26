package uk.co.kleindelao.demo.airportdisplay;

import java.util.List;

public class ResponseTableRenderer {
  private static final String DIVIDER = '|' + "-".repeat(70) + '|' + '\n';

  public String renderFlights(final List<Flight> flights) {
    final var responseTableBuilder = new StringBuilder();
    responseTableBuilder.append(DIVIDER);
    responseTableBuilder.append(
        "| Departure Time | Destination | Destination IATA Code | Flight Number |\n");
    responseTableBuilder.append(DIVIDER);
    flights.stream()
           .map(ResponseTableRenderer::render)
           .forEach(responseTableBuilder::append);
    responseTableBuilder.append(DIVIDER);
    return responseTableBuilder.toString();
  }

  private static String render(final Flight flight) {
    return String.format("|           %1$tH:%1$tM | %2$11s | %3$21s | %4$13s |\n",
        flight.departureTime(), flight.destination(), flight.destinationIataCode(),
        flight.number());
  }
}
