package ie.umbrella.db.bikes.controllers;

import ie.umbrella.db.bikes.entity.BikeStation;
import ie.umbrella.db.bikes.entity.MapMarker;
import ie.umbrella.db.bikes.service.DBAPIServiceProxy;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller is called when any of the endpoints are visited.
 *
 */


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc

class BikeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    DBAPIServiceProxy dbAPIService;

    @Test
    void getContracts() throws Exception{

        //setup
        BikeStation bikeStation = new BikeStation();
        List<BikeStation> bikeStations = new ArrayList<>();
        bikeStation.setName("testBikeStation");
        bikeStations.add(bikeStation);

        //when
        Mockito.when(dbAPIService.getContracts()).thenReturn(bikeStations);

        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/contracts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(""))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getStations() throws Exception{
        //setup
        BikeStation bikeStation = new BikeStation();
        List<BikeStation> bikeStations = new ArrayList<>();
        bikeStation.setName("testBikeStation");
        bikeStations.add(bikeStation);

        //when
        Mockito.when(dbAPIService.getContracts()).thenReturn(bikeStations);

        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/stations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getMap() throws Exception{
        //setup
        MapMarker marker1 = new MapMarker("address1", 123d, 234d);
        MapMarker marker2 = new MapMarker("address2", 456d, 789d);
        List<MapMarker> markers = new ArrayList<MapMarker>();
        markers.add(marker1);
        markers.add(marker2);
        //when
        Mockito.when(dbAPIService.getStationLocations()).thenReturn(markers);

        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/map")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("asd"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}