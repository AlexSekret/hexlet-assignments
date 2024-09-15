package exercise;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
//import java.nio.file.StandardOpenOption;

// BEGIN
public class App {
    public static void save(Path filePath, Car obj) throws JsonProcessingException {
        String serialisedCar = obj.serialize();
        try {
            Files.writeString(filePath, serialisedCar);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Car extract(Path filePath) throws IOException {
        String jsonCar = Files.readString(filePath);
        return Car.deserialize(jsonCar);
    }
}
// END
