package ie.umbrella.db.bikes.service;

import ie.umbrella.db.bikes.entity.BikeStation;
import ie.umbrella.db.bikes.entity.DBStation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DBAPIServiceProxyTest {

    @Autowired
    @Qualifier("recorded")
    DBAPIService dbapiServiceProxy;

    @Test
    public void getContracts() {
        List<BikeStation> contracts = dbapiServiceProxy.getContracts();
        String expectedName = "dublin";
        String expectedCountryCode = "IE";
        assertEquals(expectedName, contracts.get(4).getName());
        assertEquals(expectedCountryCode, contracts.get(4).getCountry_code());
    }

    @Test
    void getStations() {
        List<DBStation> stations = dbapiServiceProxy.getStations();
        int expectedNumber = 20;
        String expectedName = "James Street East";
        assertEquals(expectedNumber, stations.get(4).getNumber());
        assertEquals(expectedName, stations.get(4).getAddress());
    }
}