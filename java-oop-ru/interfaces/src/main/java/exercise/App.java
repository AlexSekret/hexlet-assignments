package exercise;

import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class App {
    public static List<String> buildApartmentsList(List<Home> appartments, int take) {
        var result = appartments.stream()
                .sorted(Home::compareTo)
                .map(Home::toString)
                .limit(take)
                .collect(Collectors.toList());
        return result;
    }
}
// END
