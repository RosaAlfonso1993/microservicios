package io.redbee.socialnetwork.users.userController;



import io.redbee.socialnetwork.users.User;
import io.redbee.socialnetwork.users.services.UserService;
import io.redbee.socialnetwork.users.usersmappers.UserRequest;
import io.redbee.socialnetwork.users.usersmappers.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Joaco Campero
 * <p>
 * created at 6/9/21
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> getAllUsers() {
        return userService.get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse postUser(@RequestBody UserRequest newUser) {
        return userService.save(newUser);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getById(@PathVariable int id){
        return this.userService.getById(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse updateUser(@PathVariable int id, @RequestBody UserRequest user){
        return this.userService.updateUser(id,user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse deleteUser(@PathVariable int id){
        return this.userService.deleteUser(id);
    }
}
