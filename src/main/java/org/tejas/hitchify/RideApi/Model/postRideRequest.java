package org.tejas.hitchify.RideApi.Model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

@Getter
@Setter
public class postRideRequest {

    private String Drivername;
    private String startPoint;
    private String endPoint;
    private GeoJsonPoint startGeoPoint;
    private GeoJsonPoint endGeoPoint;
}