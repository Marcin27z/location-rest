package enigma.pozycjarest;

import enigma.pozycjarest.model.Location;
import enigma.pozycjarest.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MyRestController {

    private LocationService locationService;

    @Autowired
    MyRestController(LocationService locationService) {
        this.locationService = locationService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveLocation", consumes = "application/json")
    public ResponseEntity saveLocation(@RequestBody Location location) {
        locationService.saveLocation(location);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getAll")
    public Iterable<Location> getAll() {
        return locationService.getAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get/{id}")
    public Iterable<Location> getAll(@PathVariable String id) {
        return locationService.getLocationsByDeviceId(id);
    }
}
