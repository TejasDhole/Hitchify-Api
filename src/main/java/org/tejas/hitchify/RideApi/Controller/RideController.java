package org.tejas.hitchify.RideApi.Controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tejas.hitchify.RideApi.Entities.RideData;
import org.tejas.hitchify.RideApi.Model.postRideRequest;
import org.tejas.hitchify.RideApi.Model.postRideResponse;
import org.tejas.hitchify.RideApi.Repository.RideRepository;


import java.util.List;

@RestController
public class RideController {


    @Autowired
    private RideRepository rideRepository ;
    @GetMapping("/Rides")
    public ResponseEntity<List<RideData>> getAllRides(){
List<RideData> rides = rideRepository.findAll();
        return new ResponseEntity<>(rides, HttpStatus.OK);
    }

    
    @PostMapping("/Rides")
    public ResponseEntity<postRideResponse> postRide(@RequestBody postRideRequest ride) {
        postRideResponse ridedata = new postRideResponse();
        try {


            ridedata = rideRepository.save(ride);
            return new ResponseEntity<>(ridedata, HttpStatus.CREATED);
        } catch (Exception e) {

            return new ResponseEntity<>(ridedata, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/Rides/{id}")
    public ResponseEntity<postRideResponse> getRideById(@PathVariable String id) {
        postRideResponse ride = rideRepository.findById(id);
        return new ResponseEntity<>(ride, HttpStatus.OK);
    }

    @GetMapping("/Rides1")
    public List<postRideResponse> getRideByQueryParams(@RequestParam(value = "startGeoPoint") GeoJsonPoint startGeoPoint,
                                                        @RequestParam(value = "endGeoPoint") GeoJsonPoint endGeoPoint) {
        return rideRepository.findByQueryParams( startGeoPoint, endGeoPoint);
    }



}
