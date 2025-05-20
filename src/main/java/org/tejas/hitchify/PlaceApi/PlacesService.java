package org.tejas.hitchify.PlaceApi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PlacesService {

    @Value("${google.api.key}")
    private String apiKey;

    public String fetchPlaces(String input, String location, String radius, String language, String components) {
        String url = String.format(
                "https://maps.googleapis.com/maps/api/place/autocomplete/json?input=%s&location=%s&radius=%s&language=%s&components=%s&key=%s",
                input, location, radius, language, components, apiKey
        );

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }
}
