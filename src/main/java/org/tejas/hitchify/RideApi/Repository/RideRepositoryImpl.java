package org.tejas.hitchify.RideApi.Repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Circle;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.tejas.hitchify.RideApi.Entities.RideData;
import org.tejas.hitchify.RideApi.Model.postRideRequest;
import org.tejas.hitchify.RideApi.Model.postRideResponse;

import java.util.ArrayList;
import java.util.List;

import static java.util.UUID.randomUUID;

@Slf4j
@Repository
public class RideRepositoryImpl  implements RideRepository {


    private final MongoTemplate mongoTemplate;


    @Autowired
    public RideRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<RideData> findAll() {
        return mongoTemplate.findAll(RideData.class);
    }

    @Override
    public postRideResponse save(postRideRequest ride) {
        postRideResponse response = new postRideResponse();

        RideData rideData = new RideData();
//        String id = "ride:" + randomUUID().toString();
        rideData.setId("ride:" + randomUUID().toString());
        rideData.setDrivername(ride.getDrivername());
        rideData.setStartPoint(ride.getStartPoint());
        rideData.setEndPoint(ride.getEndPoint());
        rideData.setStartGeoPoint(ride.getStartGeoPoint());
        rideData.setEndGeoPoint(ride.getEndGeoPoint());
        rideData.setFare(ride.getFare());
        rideData.setAbout(ride.getAbout());
        rideData.setDate(ride.getDate());
//        rideData.setTime(ride.getTime());
        rideData.setPassenger(ride.getPassenger());




        RideData savedRide =  mongoTemplate.save(rideData);

        response.setId(savedRide.getId());
        response.setDrivername(savedRide.getDrivername());
        response.setStartPoint(savedRide.getStartPoint());
        response.setEndPoint(savedRide.getEndPoint());
        response.setStartGeoPoint(savedRide.getStartGeoPoint());
        response.setEndGeoPoint(savedRide.getEndGeoPoint());
        response.setFare(savedRide.getFare());
        response.setDate(savedRide.getDate());
//        response.setTime(savedRide.getTime());
        response.setPassenger(savedRide.getPassenger());
        response.setAbout(savedRide.getAbout());


       return response;
    }

    public postRideResponse findById(String id) {
        RideData rideData = mongoTemplate.findById(id, RideData.class);
        postRideResponse response = new postRideResponse();
        assert rideData != null;
        response.setId(rideData.getId());
        response.setDrivername(rideData.getDrivername());
        response.setStartPoint(rideData.getStartPoint());
        response.setEndPoint(rideData.getEndPoint());
        response.setStartGeoPoint(rideData.getStartGeoPoint());
        response.setEndGeoPoint(rideData.getEndGeoPoint());
        return response;
    }

    @Override
    public List<postRideResponse> findByQueryParams(GeoJsonPoint startGeoPoint, GeoJsonPoint endGeoPoint) {
        double startLongitude =startGeoPoint.getX();
        double startLatitude =startGeoPoint.getY();
        double startRadius = 100.0 / 6371.0;

        double endLongitude =endGeoPoint.getX();
        double endLatitude =endGeoPoint.getY();
        double endRadius = 100.0 / 6371.0;

        Criteria startCriteria = Criteria.where("startGeoPoint").withinSphere(new Circle(startLongitude, startLatitude, startRadius));
        Criteria endCriteria = Criteria.where("endGeoPoint").withinSphere(new Circle(endLongitude, endLatitude, endRadius));
        Query query = new Query(new Criteria().andOperator(startCriteria, endCriteria));

        List<RideData> rideData = mongoTemplate.find(query, RideData.class);
        List<postRideResponse> responseList = new ArrayList<>();
        for(RideData ride : rideData){
            postRideResponse response = new postRideResponse();
            response.setId(ride.getId());
            response.setDrivername(ride.getDrivername());
            response.setStartPoint(ride.getStartPoint());
            response.setEndPoint(ride.getEndPoint());
            response.setStartGeoPoint(ride.getStartGeoPoint());
            response.setEndGeoPoint(ride.getEndGeoPoint());
            responseList.add(response);
        }
        return responseList;
    }



}
