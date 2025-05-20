package org.tejas.hitchify.UserApi.Entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "Users")
public class UserData {

        @Id
        private String id;
        private String name;
        private String email;
        private String phone;
}
