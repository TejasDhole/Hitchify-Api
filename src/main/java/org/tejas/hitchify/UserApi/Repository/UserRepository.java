package org.tejas.hitchify.UserApi.Repository;

import org.tejas.hitchify.UserApi.Entities.UserData;
import org.tejas.hitchify.UserApi.Model.PostUserRequest;
import org.tejas.hitchify.UserApi.Model.PostUserResponse;

import java.util.*;

public interface UserRepository {

    List<UserData> findAll();

    PostUserResponse save(PostUserRequest user);

    PostUserResponse findById(String id);

    PostUserResponse updateUser(String id, PostUserRequest user);


    List<UserData> findByEmailOrPhone(String email, String phone);
}
