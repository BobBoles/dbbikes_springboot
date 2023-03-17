package ie.umbrella.db.bikes.controllers;

import ie.umbrella.db.bikes.entity.BikeStation;
import ie.umbrella.db.bikes.entity.DBStation;
import ie.umbrella.db.bikes.entity.MapMarker;
import ie.umbrella.db.bikes.service.DBAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 *
 * The controller sets the Model
 * Then returns a view name which is then mapped to a ThymeLeaf template.
 * The template displays a view of the Model
 *
 */

@Controller
public final class BikeController {

    @Autowired
    @Qualifier("recorded")
    DBAPIService apiService;

    @GetMapping("/contracts")
    public String getContracts(Model model) {
        List<BikeStation> data = apiService.getContracts();
        model.addAttribute("data", data);
        return "JCDecaux";
    }

    @GetMapping("/stations")
    public String getStations(Model model){
        List<DBStation> data = apiService.getStations();
        model.addAttribute("data", data);
        return "dbstations";
    }

    @GetMapping("/map")
    public String getMap(Model model){
        List<MapMarker> markers = apiService.getStationLocations();
        model.addAttribute("markers", markers);
        return "map";
    }
}