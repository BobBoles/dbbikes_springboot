package ie.umbrella.db.bikes.config;

import ie.umbrella.db.bikes.entity.RecordedResponse;
import ie.umbrella.db.bikes.repository.RecordedResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;


/**
 * Used to initialisein-memory H2 database with JSON data from files.
 *
 * The file data was retrieved using the api in an earlier session.
 * The aim is to allow the application to be run using 'recorded' data.
 * (the api is unavailable without an api key)
 */

@Component
public class Runner implements CommandLineRunner {

    @Autowired
    RecordedResponseRepository recordedResponseRepository;

    @Override
    public void run(String... args) throws Exception {
        String url = "https://api.jcdecaux.com/vls/v1/stations";
        saveRecordedResponse(url, "stations");

        url = "https://api.jcdecaux.com/vls/v1/contracts";
        saveRecordedResponse(url, "contracts");

        /*
        The JSON data used to display location info on the map
        is the same JSON data used to display a table containing station information
        Ignored here to avoid duplicates in the database

        url = "https://api.jcdecaux.com/vls/v1/stations";
        content = readJsonFromFile("locations");
        saveRecordedResponse(url, content);
        */
    }

    /**
     * Stores the data using repository pattern
     *
     * @param url used later as key to retrieve JSON from database
     * @param fileName the flat file containing jsonData
     */

    private void saveRecordedResponse(String url, String fileName){
        String jsonContentFromFile = readJsonFromFile(fileName);
        RecordedResponse recordedResponse = new RecordedResponse();
        recordedResponse.setUrl(url);
        recordedResponse.setJsonData(jsonContentFromFile);
        recordedResponseRepository.save(recordedResponse);
    }

    /**
     *
     * Reads json data from flatfiles
     *
     * @param filename
     * @return the contents of the file
     */

    private String readJsonFromFile(String filename){
        String fileContents = null;
        try {
            Resource resource = new ClassPathResource("jsondata/" + filename + ".json");
            InputStream inputStream = resource.getInputStream();
            byte[] byteArray = FileCopyUtils.copyToByteArray(inputStream);
            fileContents = new String(byteArray, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContents;
    }
}
