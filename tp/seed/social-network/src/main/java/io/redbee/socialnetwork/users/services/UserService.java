package io.redbee.socialnetwork.users.services;

import io.redbee.socialnetwork.users.User;
import io.redbee.socialnetwork.users.UserDao;
import io.redbee.socialnetwork.users.enums.EnumStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao){
        this.userDao = userDao;
    }

    public List<User> get() {
        return this.userDao.get();
    }

    public User save(User user) {
        return this.userDao.save(user).orElseThrow();
    }

    public Optional<User> getById(int id){
        return this.userDao.getById(id);
    }

    public void updateUser(User newData){
        this.userDao.update(newData);
    }

    public void deleteUser(int id){
        this.userDao.getById(id)
                .map(user -> this.userDao.update(user.withStatus(EnumStatus.DELETED.toString())))
                .orElseThrow();
    }
}
