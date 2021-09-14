package io.redbee.socialnetwork.users.usersmappers;

import lombok.Value;

@Value
public class UserRequest {
    private String mail;
    private String password;
}
