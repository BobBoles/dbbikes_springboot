package ie.umbrella.db.bikes.entity;

/**
 * A POJO to allow Jackson map JSON to the Model returned to ThymeLeaf
 * Used by Leaflet to display the location of Dublin Bike stations
 *
 * https://leafletjs.com/
 * https://www.openstreetmap.org/
 *
 */

public class Position {
    private double lat;
    private double lng;

    public Position() {
    }

    public Position(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    // getters and setters

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}