package org.tejas.hitchify.RideApi.Entities;

import com.mongodb.lang.NonNull;
import com.mongodb.lang.Nullable;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Time;
import java.util.Date;

@Getter
@Setter

@Document(collection = "Rides")
public class RideData {


    @Id
    private String id;
    private String Drivername;
//    private String Driverid;
    private String startPoint;
    private String endPoint;
    private GeoJsonPoint startGeoPoint;
    private GeoJsonPoint endGeoPoint;
    private Date date;
//    private Time time;
    private int passenger;
    private int fare;
    private String about;


//    private String journeyDate;
//    private String journeyCompleteTime;
//    private String journeyStartTime;
//    private String seats;
//    private String price;
//    private String contact;
//    private String vehicle;
//    private String vehicleNumber;
//    private String vehicleModel;
//    private String vehicleColor;
//    private String vehicleCapacity;
//    private String vehicleType;

}
