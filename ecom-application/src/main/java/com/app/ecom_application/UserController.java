package com.app.ecom_application;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/users")
public class UserController {

    private final UserService userService;
    // Its constructor is created by RequiredArgsConstructor and is used to DI userService in userController

    @GetMapping //("/api/users")
//    @RequestMapping(value = "/api/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUser() {
        return new ResponseEntity<>(userService.fetchAllUsers(), HttpStatus.OK);
        // return ResponseEntity.ok(userService.fetchAllUsers());
        // Can display using this what response you want to give the user - Customizable Status code
    }

    @PostMapping//("/api/users")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        userService.createUser(user);
        return new ResponseEntity<>("User added successfully", HttpStatus.OK);
    }

    //get particular user
    @GetMapping("/{id}")//("/api/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
//        User user = userService.fetchUser(id);
//        if (user == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(user);
        return userService.fetchUser(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody User updateUser) {
        boolean updated = userService.updateUser(id, updateUser);
        if (updated) {
            return ResponseEntity.ok("User updated successfully");
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}

/*
/api/user is repeating at each method can globally define requestMapping
 */
