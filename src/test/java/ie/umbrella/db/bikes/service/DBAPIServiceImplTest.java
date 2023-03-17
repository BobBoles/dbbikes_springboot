package ie.umbrella.db.bikes.service;

import ie.umbrella.db.bikes.entity.BikeStation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DBAPIServiceImplTest {

    @Autowired
    @Qualifier("api")
    DBAPIServiceImpl dbapiServiceImpl;

    @Test
    void getContracts() {
        List<BikeStation> contracts = dbapiServiceImpl.getContracts();
        String expectedName = "dublin";
        String expectedCountryCode = "IE";
        assertEquals(expectedName, contracts.get(4).getName());
        assertEquals(expectedCountryCode, contracts.get(4).getCountry_code());
    }

    @Test
    void getStations() {
    }

    @Test
    void getStationLocations() {
    }
}