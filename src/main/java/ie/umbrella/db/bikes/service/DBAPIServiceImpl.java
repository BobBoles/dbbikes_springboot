package ie.umbrella.db.bikes.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ie.umbrella.db.bikes.entity.BikeStation;
import ie.umbrella.db.bikes.entity.DBStation;
import ie.umbrella.db.bikes.entity.MapMarker;
import ie.umbrella.db.bikes.exception.ServiceLayerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 *
 * Mocks the API
 *
 * Retrieves JSON data from the JCDecaux Dublin Bikes API
 * Use the application.properties file to ensure the api is used
 * The standard Jackson library is used to map the JSON to a collection of Java objects
 * THe java colllection is returned to the Controller
 *
 */

@Component("api")
public class DBAPIServiceImpl implements DBAPIService {

    private static final Logger logger = LoggerFactory.getLogger(DBAPIServiceImpl.class);

    /**
     * Obtain an api key from JCDecaux and set in application.properties
     */
    @Value("${dublinbikes_apikey}")
    private String apiKey;

    public List<BikeStation> getContracts() throws ServiceLayerException{
        logger.info("Retrieving contract info from the JCDecaux API");
        List<BikeStation> contracts = null;
        try{
            String apiUrl = "https://api.jcdecaux.com/vls/v1/contracts?apiKey=" + apiKey + "&contract=dublin";
            RestTemplate restTemplate = new RestTemplate();
            String jsonResponse = restTemplate.getForObject(apiUrl, String.class);
            logger.info("jsonResponse:" + jsonResponse);
            ObjectMapper objectMapper = new ObjectMapper();
            contracts = objectMapper.readValue(jsonResponse, new TypeReference<List<BikeStation>>(){});
        }
        catch(JsonProcessingException e)
        {
            logger.error("JsonProcessingException retrieving contracts: ", e.getMessage());
            throw new ServiceLayerException("JsonProcessingException retrieving contracts", e);
        }
        catch(Exception e)
        {
            logger.error("Exception retrieving contracts: ", e.getMessage());
            throw new ServiceLayerException("Exception retrieving contracts", e);
        }
        logger.info("Number of contracts retrieved:" + contracts.size());
        return contracts;
    }

    public List<DBStation> getStations() throws ServiceLayerException{
        logger.info("Retrieving stations info from the JCDecaux API");
        List<DBStation> stations = null;
        try{
            String apiUrl = "https://api.jcdecaux.com/vls/v1/stations?apiKey=" + apiKey + "&contract=dublin";
            RestTemplate restTemplate = new RestTemplate();
            String jsonResponse = restTemplate.getForObject(apiUrl, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            stations = objectMapper.readValue(jsonResponse, new TypeReference<List<DBStation>>(){});
        }
        catch(JsonProcessingException e)
        {
            logger.error("JsonProcessingException retrieving stations: ", e.getMessage());
            throw new ServiceLayerException("Exception retrieving stations", e);
        }
        catch(Exception e)
        {
            logger.error("Exception retrieving contracts: ", e.getMessage());
            throw new ServiceLayerException("Exception retrieving stations", e);
        }
        logger.info("Number of stations retrieved:" + stations.size());
        return stations;
    }

    public List<MapMarker> getStationLocations() throws ServiceLayerException{
        logger.info("Retrieving stations marker info from the JCDecaux API");
        List<MapMarker> mapMarkers = null;
        try{
            RestTemplate restTemplate = new RestTemplate();
            String apiUrl = "https://api.jcdecaux.com/vls/v1/stations?apiKey=" + apiKey + "&contract=dublin";
            String jsonResponse = restTemplate.getForObject(apiUrl, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            mapMarkers = objectMapper.readValue(jsonResponse, new TypeReference<List<MapMarker>>(){});
        }
        catch(JsonProcessingException e)
        {
            logger.error("JsonProcessingException retrieving locations: ", e.getMessage());
            throw new ServiceLayerException("Exception retrieving locations", e);
        }
        catch(Exception e)
        {
            logger.error("Exception retrieving contracts: ", e.getMessage());
            throw new ServiceLayerException("Exception retrieving locations", e);
        }
        logger.info("Number of mapMarkers retrieved:" + mapMarkers.size());
        return mapMarkers;
    }

    @ExceptionHandler(ServiceLayerException.class)
    public ResponseEntity<String> handleServiceLayerException(ServiceLayerException e) {
        return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}