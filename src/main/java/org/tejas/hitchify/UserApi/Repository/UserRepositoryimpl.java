package org.tejas.hitchify.UserApi.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import org.tejas.hitchify.UserApi.Entities.UserData;
import org.tejas.hitchify.UserApi.Model.PostUserRequest;
import org.tejas.hitchify.UserApi.Model.PostUserResponse;

import java.util.List;

import static java.util.UUID.randomUUID;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Repository
public class UserRepositoryimpl implements UserRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public UserRepositoryimpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<UserData> findAll() {
        return mongoTemplate.findAll(UserData.class);
    }

    @Override
    public PostUserResponse save(PostUserRequest user) {
        PostUserResponse response = new PostUserResponse();

        UserData userData = new UserData();
        String id = "user:" + randomUUID().toString();
        userData.setId(id);
        userData.setName(user.getName());
        userData.setEmail(user.getEmail());
        userData.setPhone(user.getPhone());

        UserData savedUser  =mongoTemplate.save(userData);

response.setId(savedUser.getId());
response.setName(savedUser.getName());
response.setEmail(savedUser.getEmail());
response.setPhone(savedUser.getPhone());

return response;
    }


    @Override
    public PostUserResponse findById(String id) {
        UserData userData = mongoTemplate.findById(id, UserData.class);
        PostUserResponse response = new PostUserResponse();
        response.setId(userData.getId());
        response.setName(userData.getName());
        response.setEmail(userData.getEmail());
        response.setPhone(userData.getPhone());
        return response;


    }

    public PostUserResponse updateUser(String id, PostUserRequest user) {
        UserData userData = mongoTemplate.findById(id, UserData.class);
        if (userData == null) {
            return null;
//            throw new UserNotFoundException("User with id " + id + " not found");
        }


        if(user.getName() != null)
            userData.setName(user.getName());
        if(user.getEmail() != null)
            userData.setEmail(user.getEmail());
        if(user.getPhone() != null)
            userData.setPhone(user.getPhone());


        UserData updatedUser = mongoTemplate.save(userData);
        System.out.println(updatedUser);

        PostUserResponse response = new PostUserResponse();
        response.setId(updatedUser.getId());
        response.setName(updatedUser.getName());
        response.setEmail(updatedUser.getEmail());
        response.setPhone(updatedUser.getPhone());

        return response;
    }

    @Override
    public List<UserData> findByEmailOrPhone(String email, String phone) {
        if(email != null){
            return mongoTemplate.find(query(where("email").is(email)), UserData.class);
        }
        if(phone != null){
            return mongoTemplate.find(query(where("phone").is(phone)), UserData.class);
        }
        return null;

    }

}
