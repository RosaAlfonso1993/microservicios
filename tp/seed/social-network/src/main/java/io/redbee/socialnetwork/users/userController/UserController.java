package io.redbee.socialnetwork.users.userController;



import io.redbee.socialnetwork.users.User;
import io.redbee.socialnetwork.users.services.UserService;
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
    public List<User> getAllUsers() {
        return userService.get();
    }


    @PostMapping
    public ResponseEntity<User> postUser(@RequestBody User newUser) {
        return new ResponseEntity<>(userService.save(newUser), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable int id){
        return new ResponseEntity<>(this.userService.getById(id).orElse(null), HttpStatus.OK);
    }

    @PutMapping("")
    public void updateUser(@RequestBody User user){
        this.userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id){
        this.userService.deleteUser(id);
    }
}
