package enigma.pozycjarest.repository;

import enigma.pozycjarest.model.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {

    Location save(Location location);

    Iterable<Location> findAll();

    Iterable<Location> findLocationsByDeviceId(String deviceId);
}
