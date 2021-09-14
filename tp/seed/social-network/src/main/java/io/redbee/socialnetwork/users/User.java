package io.redbee.socialnetwork.users;

import lombok.Builder;
import lombok.Data;
import lombok.With;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@Builder(toBuilder = true)
public class User {
    private final Integer id;
    private final String mail;
    private final String encryptedPassword;
    @With
    private final String status;
    private final LocalDateTime creationDate;
    private final String creationUser;
    private final LocalDateTime modificationDate;
    private final String modificationUser;
}
