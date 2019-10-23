package locationrest.service;

import locationrest.model.Location;
import locationrest.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LocationService {

    private LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Location saveLocation(Location location) {
        return locationRepository.save(location);
    }

    public Iterable<Location> getLocationsByDeviceId(String deviceId) {
        return locationRepository.findLocationsByDeviceId(deviceId);
    }

    public Iterable<Location> getAll() {
        return locationRepository.findAll();
    }
}
