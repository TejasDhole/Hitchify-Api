package org.tejas.hitchify.UserApi.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tejas.hitchify.UserApi.Model.PostUserRequest;
import org.tejas.hitchify.UserApi.Model.PostUserResponse;
import org.tejas.hitchify.UserApi.Entities.UserData;
import org.tejas.hitchify.UserApi.Repository.UserRepository;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    //test purpose only
    @GetMapping("/users")
    public ResponseEntity<List<UserData>> getAllUsers(){
        List<UserData> users = userRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @GetMapping("/users/details")
    public ResponseEntity<List<UserData>> findByEmailOrPhone(
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phone) {
        List<UserData> users = userRepository.findByEmailOrPhone(email, phone);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }



    @PostMapping("/users")
    public ResponseEntity<PostUserResponse> createUser(@RequestBody PostUserRequest user){
        PostUserResponse response = userRepository.save(user);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<PostUserResponse> getUserById(@PathVariable String id){
        PostUserResponse user = userRepository.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<PostUserResponse> updateUser(@PathVariable String id, @RequestBody PostUserRequest user){
        PostUserResponse response = userRepository.updateUser(id,user);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }




}
