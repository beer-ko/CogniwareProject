import controller.CSVToJSON;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CSVToJSON_test {

        @BeforeEach
        void setUp() {

        }

        @Test
        void testConvertCSVToJSON() throws IOException {
            CSVToJSON.convertCSVToJSON("resources/input.csv", "resources/jsonfile.json", "id,name,color,age,weight");
            String expectedJson = "{\"rows\":[{\"id\":\"1\",\"name\":\"John Doe\",\"color\":\"Red\",\"age\":30,\"weight\":120.0},{\"id\":\"2\",\"name\":\"Jane Smith\",\"color\":\"Blue\",\"age\":28,\"weight\":115.0},{\"id\":\"3\",\"name\":\"Michael Brown\",\"color\":\"Green\",\"age\":35,\"weight\":130.0}]}";
            String actualJson = new String(Files.readAllBytes(Paths.get("resources/jsonfile.json")));
            assertEquals(expectedJson, actualJson);
        }

        @Test
        void testConvertCSVToJSONWithEmptyCSV() throws IOException {
            CSVToJSON.convertCSVToJSON("resources/emptyinput.csv", "resources/jsonfile.json", "id,name,color,age,weight");
            String expectedJson = "{\"rows\":[]}";
            String actualJson = new String(Files.readAllBytes(Paths.get("resources/jsonfile.json")));
            assertEquals(expectedJson, actualJson);
        }

        @Test
        void testConvertCSVToJSONWithEmptyHeader() throws IOException {
            CSVToJSON.convertCSVToJSON("resources/input.csv", "resources/jsonfile.json", "");
            String expectedJson = "{\"rows\":[{\"0\":\"1\",\"1\":\"John Doe\",\"2\":\"Red\",\"3\":\"30\",\"4\":\"120.0\"},{\"0\":\"2\",\"1\":\"Jane Smith\",\"2\":\"Blue\",\"3\":\"28\",\"4\":\"115.0\"},{\"0\":\"3\",\"1\":\"Michael Brown\",\"2\":\"Green\",\"3\":\"35\",\"4\":\"130.0\"}]}";
            String actualJson = new String(Files.readAllBytes(Paths.get("resources/jsonfile.json")));
            assertEquals(expectedJson, actualJson);
        }

        @Test
        void testConvertCSVToJSONWithInvalidCSV() throws IOException {
           CSVToJSON.convertCSVToJSON("resources/invalidinput.csv", "resources/jsonfile.json", "id,name,color,age,weight");
            String expectedJson = "{\"rows\":[{\"id\":\"1\",\"name\":\"John Doe\",\"color\":\"Red\",\"age\":30,\"weight\":120.0},{\"id\":\"2\",\"name\":\"Jane Smith\",\"color\":\"Blue\",\"age\":28,\"weight\":115.0},{\"id\":\"3\",\"name\":\"Michael Brown\",\"color\":\"Green\",\"age\":35,\"weight\":130.0}]}";
            String actualJson = new String(Files.readAllBytes(Paths.get("resources/jsonfile.json")));
            assertEquals(expectedJson, actualJson);

        }
}
