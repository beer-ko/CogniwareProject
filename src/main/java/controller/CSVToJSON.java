package controller;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVToJSON {

    public static void convertCSVToJSON(String csvFile, String jsonFile, String headersString) {
        List<String> headers = Arrays.asList(headersString.split(","));
        List<String> fileHeaders = Arrays.asList(readCSV(csvFile, true).get(0));
        // Read the CSV file and store the data in a list of strings arrays
        List<String[]> data = readCSV(csvFile, false);
        // Create a new JSON array to store the converted data
        JSONArray jsonArray = new JSONArray();

        int jsonFileIndex = 0;
        // Iterate through each row of the CSV data
        for (String[] row : data) {

            // Create a new JSON object to store each row of data
            JSONObject jsonObject = new JSONObject();
            // Iterate through each element in the row
            for (int i = 0; i < row.length; i++) {
                // Add the element to the JSON object with the corresponding header as the key
                if (headers.contains(fileHeaders.get(i))) {
                    jsonObject.put(fileHeaders.get(i), row[i]);
                }
            }
            // Add the JSON object to the JSON array
            jsonArray.put(jsonObject);
            // Write the JSON array to a JSON file
            try (FileWriter fileWriter = new FileWriter(jsonFile.replace(".json", jsonFileIndex++ + ".json"))) {
                fileWriter.write(jsonArray.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            jsonArray = new JSONArray();
        }


    }

    public static List<String[]> readCSV(String csvFile, boolean getHeaders) {
        // Create a list to store the CSV data
        List<String[]> data = new ArrayList<>();
        // Read the CSV file and store the headers
        try (CSVReader csvReader = new CSVReader(new FileReader(csvFile.replace("\\", "/")))) {
            if (getHeaders) {
                String[] row = csvReader.readNext();
                data.add(row);
            }else {
                // Read the remaining rows
                csvReader.readNext(); // skip headers
                String[] row;
                while ((row = csvReader.readNext()) != null) {
                    // Add the row to the data list
                    data.add(row);
                }
            }
        } catch (IOException | CsvException e) {

            e.printStackTrace();
        }
        // Return the CSV data
        return data;
    }
}
