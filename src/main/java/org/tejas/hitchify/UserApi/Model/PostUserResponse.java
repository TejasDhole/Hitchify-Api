package org.tejas.hitchify.UserApi.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostUserResponse {

    private String id;
    private String name;
    private String email;
    private String phone;
}
