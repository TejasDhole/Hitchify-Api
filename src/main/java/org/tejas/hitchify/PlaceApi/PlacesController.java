package org.tejas.hitchify.PlaceApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class PlacesController {

    @Value("${google.api.key}")
    private String apiKey;

    @Autowired
    private PlacesService placesService;

    @GetMapping("/api/places")
    public ResponseEntity<String> getPlaces(
            @RequestParam String input,
            @RequestParam String location,
            @RequestParam String radius,
            @RequestParam String language,
            @RequestParam String components) {

        String response = placesService.fetchPlaces(input, location, radius, language, components);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/place-details")
    public ResponseEntity<?> getPlaceDetails(@RequestParam("placeId") String placeId) {
        String url = String.format(
                "https://maps.googleapis.com/maps/api/place/details/json?place_id=%s&key=%s",
                placeId, apiKey);

        RestTemplate restTemplate = new RestTemplate();

        try {
            String response = restTemplate.getForObject(url, String.class);
            return ResponseEntity.ok(response); // Forward response to frontend
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error while fetching place details: " + e.getMessage());
        }
}}
