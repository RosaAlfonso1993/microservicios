package io.redbee.socialnetwork.users.services;

import io.redbee.socialnetwork.shared.exception.UserNotFountException;
import io.redbee.socialnetwork.users.UserDao;
import io.redbee.socialnetwork.users.enums.EnumStatus;
import io.redbee.socialnetwork.users.usersmappers.UserMap;
import io.redbee.socialnetwork.users.usersmappers.UserRequest;
import io.redbee.socialnetwork.users.usersmappers.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserDao userDao;
    private final UserMap userMap;

    @Autowired
    public UserService(UserDao userDao, UserMap userMap){
        this.userDao = userDao;
        this.userMap = userMap;
    }

    public List<UserResponse> get() {
        return this.userDao.get().stream()
                .map(userMap::userResponseFromMap)
                .collect(Collectors.toList());
    }

    public UserResponse save(UserRequest userRequest) {
        return userDao
                .save(userMap.userCreateFromMap(userRequest))
                .map(userMap::userResponseFromMap)
                .orElseThrow();
    }

    public UserResponse getById(int id){
        return this.userDao.getById(id)
                .map(userMap::userResponseFromMap)
                .orElseThrow(UserNotFountException::new);
    }

    public UserResponse updateUser(int id, UserRequest userRequest){
        return this.userDao.getById(id)
                .map(user -> userMap.userUpdateFromMap(user,userRequest))
                .map(userDao::update)
                .map(userMap::userResponseFromMap)
                .orElseThrow();
    }

    public UserResponse deleteUser(int id){
        return this.userDao.getById(id)
                .map(user -> userDao.update(user.withStatus(EnumStatus.DELETED.toString())))
                .map(userMap::userResponseFromMap)
                .orElseThrow();
    }
}
