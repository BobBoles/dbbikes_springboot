package ie.umbrella.db.bikes.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * A POJO to allow Jackson map JSON to the Model returned to ThymeLeaf
 * Used by Leaflet to display the location of Dublin Bike stations
 *
 * https://leafletjs.com/
 * https://www.openstreetmap.org/
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class MapMarker {

    private Long number;
    private String address;
    private Position position;

    public MapMarker() {
    }

    public MapMarker(String address, Double lat, Double lng) {
        this.address = address;
        this.position = new Position(lat, lng);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }
}
