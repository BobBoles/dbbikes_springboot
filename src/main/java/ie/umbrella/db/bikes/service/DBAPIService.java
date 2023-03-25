package ie.umbrella.db.bikes.service;

import ie.umbrella.db.bikes.entity.BikeStation;
import ie.umbrella.db.bikes.entity.DBStation;
import ie.umbrella.db.bikes.entity.MapMarker;
import ie.umbrella.db.bikes.exception.ServiceLayerException;
import java.util.List;

/**
 * 2 implementations are provided
 *
 * DBAPIServiceImpl - uses the JCDecaux to retrieve real time data. An api key is required
 *
 * DBAPIServiceProxy - uses recorded data retrieved from an im-memory database
 */

public interface DBAPIService {
    public List<MapMarker> getStationLocations() throws ServiceLayerException;
    public List<DBStation> getStations() throws ServiceLayerException;
    public List<BikeStation> getContracts() throws ServiceLayerException;
    public List<MapMarker> getStationMarkerById(Long id) throws ServiceLayerException;
}
