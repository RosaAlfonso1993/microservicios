package io.redbee.socialnetwork.users.usersmappers;

import io.redbee.socialnetwork.users.User;
import io.redbee.socialnetwork.users.enums.EnumStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserMap {

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserMap(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User userCreateFromMap(UserRequest userRequest){
        return User.builder()
                .mail(userRequest.getMail())
                .encryptedPassword(passwordEncoder.encode(userRequest.getPassword()))
                .status(EnumStatus.CREATED.toString())
                .creationDate(LocalDateTime.now())
                .creationUser("system")
                .modificationDate(LocalDateTime.now())
                .modificationUser("system")
                .build();
    }

    public UserResponse userResponseFromMap(User user){
        return new UserResponse(user.getId(), user.getMail(), user.getStatus());
    }

    public User userUpdateFromMap(User user, UserRequest userRequest){
        return user.toBuilder()
                .mail(userRequest.getMail())
                .encryptedPassword(passwordEncoder.encode(userRequest.getPassword()))
                .modificationDate(LocalDateTime.now())
                .build();
    }

}
