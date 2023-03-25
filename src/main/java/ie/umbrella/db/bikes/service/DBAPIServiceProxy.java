package ie.umbrella.db.bikes.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ie.umbrella.db.bikes.entity.BikeStation;
import ie.umbrella.db.bikes.entity.DBStation;
import ie.umbrella.db.bikes.entity.MapMarker;
import ie.umbrella.db.bikes.entity.RecordedResponse;
import ie.umbrella.db.bikes.exception.ServiceLayerException;
import ie.umbrella.db.bikes.repository.RecordedResponseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Mocks the API
 *
 * The API Proxy retrieves JSON data from the H2 database
 * The application.properties file is used to determine if the api or proxy is used
 * This proxy uses the Repository pattern to retrieve data from the database
 * The standard Jackson library is used to map the JSON to a collection of Java objects returned to the Controller
 *
 */

@Component("recorded")
public class DBAPIServiceProxy implements DBAPIService {

    private static final Logger logger = LoggerFactory.getLogger(DBAPIServiceProxy.class);

    @Autowired
    RecordedResponseRepository recordedResponseRepository;


    @Override
    public List<MapMarker> getStationLocations() {
        List<MapMarker> mapMarkers = null;
        try{
            String identifier = "locations";
            RecordedResponse recordedResponse = recordedResponseRepository.findByIdentifier(identifier);
            String jsonData = recordedResponse.getJsonData();
            ObjectMapper objectMapper = new ObjectMapper();
            mapMarkers = objectMapper.readValue(recordedResponse.getJsonData(), new TypeReference<List<MapMarker>>(){});
        }
        catch(JsonProcessingException e)
        {
            logger.error("Exception retrieving stationLocations: ", e.getMessage());
            throw new ServiceLayerException("Exception retrieving stationLocations", e);
        }
        logger.info("MapMarkers retrieved:" + mapMarkers.size());
        return mapMarkers;
    }

    @Override
    public List<DBStation> getStations() {
        List<DBStation> stations = null;
        try{
            String identifier = "stations";
            RecordedResponse recordedResponse = recordedResponseRepository.findByIdentifier(identifier);
            String jsonData = recordedResponse.getJsonData();
            ObjectMapper objectMapper = new ObjectMapper();
            stations = objectMapper.readValue(jsonData, new TypeReference<List<DBStation>>(){});
        }
        catch(JsonProcessingException e)
        {
            logger.error("Exception retrieving stations: ", e.getMessage());
            throw new ServiceLayerException("Exception retrieving stations", e);
        }
        logger.info("Stations retrieved:" + stations.size());
        return stations;
    }

    @Override
    public List<BikeStation> getContracts() {
        List<BikeStation> contracts = null;
        try{
            String identifier = "contracts";
            RecordedResponse recordedResponse = recordedResponseRepository.findByIdentifier(identifier);
            String jsonData = recordedResponse.getJsonData();
            ObjectMapper objectMapper = new ObjectMapper();
            contracts = objectMapper.readValue(jsonData, new TypeReference<List<BikeStation>>(){});
        }
        catch(JsonProcessingException e)
        {
            logger.error("Exception retrieving contracts: ", e.getMessage());
            throw new ServiceLayerException("Exception retrieving contracts", e);
        }
        logger.info("Contracts retrieved:" + contracts.size());
        return contracts;
    }

    @Override
    public List<MapMarker> getStationMarkerById(Long id) throws ServiceLayerException {
        logger.info("DBAPIServiceImpl: Retrieving mapMarker for station" + id);
        List<MapMarker> mapMarkers = null;
        try{
            String identifier = "stations";
            RecordedResponse recordedResponse = recordedResponseRepository.findByIdentifier(identifier);
            String jsonData = recordedResponse.getJsonData();
            ObjectMapper objectMapper = new ObjectMapper();
            mapMarkers = objectMapper.readValue(jsonData, new TypeReference<List<MapMarker>>(){});
            for (MapMarker marker: mapMarkers) {
                if(marker.getNumber() == id){
                    mapMarkers = new ArrayList<MapMarker>();
                    mapMarkers.add(marker);
                }
            }
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
        return mapMarkers;
    }

    @ExceptionHandler(ServiceLayerException.class)
    public ResponseEntity<String> handleMyCustomException(ServiceLayerException e) {
        return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
