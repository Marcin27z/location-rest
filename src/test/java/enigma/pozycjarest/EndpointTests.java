package enigma.pozycjarest;

import com.fasterxml.jackson.databind.ObjectMapper;
import enigma.pozycjarest.model.Location;
import enigma.pozycjarest.service.LocationService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MyRestController.class)
class EndpointTests {

    @MockBean
    private LocationService locationService;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void getAllShouldReturnListWithAllLocations() throws Exception {
        Location location1 = new Location(0L, "2345", BigDecimal.valueOf(65.213342), BigDecimal.valueOf(87.221532));
        Location location2 = new Location(1L, "876", BigDecimal.valueOf(35.213342), BigDecimal.valueOf(57.221532));
        List<Location> list = Arrays.asList(location1, location2);
        when(locationService.getAll()).thenReturn(list);
        mockMvc.perform(get("/getAll")).andExpect(content().json(objectMapper.writeValueAsString(list)));
    }

    @Test
    void getByIdShouldReturnListWithLocationsMatchingId() throws Exception {
        List<Location> list = Collections.singletonList(
                new Location(0L, "2345", BigDecimal.valueOf(65.213342), BigDecimal.valueOf(87.221532))
        );
        when(locationService.getLocationsByDeviceId("2345")).thenReturn(list);
        mockMvc.perform(get("/get/2345")).andExpect(content().json(objectMapper.writeValueAsString(list)));
    }

    @Test
    void saveLocationShouldReturnStatusOk() throws Exception {
        Location location = new Location(0L, "2345", BigDecimal.valueOf(65.213342), BigDecimal.valueOf(87.221532));
        when(locationService.saveLocation(any(Location.class))).thenReturn(new Location());
        mockMvc.perform(post("/saveLocation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(location)))
                .andExpect(status().isOk());
    }

}
