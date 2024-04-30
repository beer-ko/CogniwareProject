import controller.CSVToJSON;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String csvFile = args[0]; //"resources/input.csv";
        String jsonFile = args[1]; //"resources/jsonfile.json";
        String headers = args[2]; //"id,name,color,age,weight";
        CSVToJSON.convertCSVToJSON(csvFile, jsonFile, headers);
    }
}