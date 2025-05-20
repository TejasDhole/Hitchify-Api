package org.tejas.hitchify.RideApi.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import lombok.Setter;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.sql.Time;
import java.util.Date;

@Getter
@Setter
public class postRideResponse {

    private String id;
    private String Drivername;
    private String startPoint;
    private String endPoint;
    private GeoJsonPoint startGeoPoint;
    private GeoJsonPoint endGeoPoint;
    private Date date;
//    private Time time;
    private int passenger;
    private int fare;
    private String about;

}
