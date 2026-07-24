package com.app.ecom_application;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private List<User> userList = new ArrayList<>();

    public List<User> fetchAllUsers() {
        return userList;
    }

    private long nextId = 1;
    public void createUser(User user) {
        user.setId(nextId++);
        userList.add(user);
    }

    public Optional<User> fetchUser(Long nextId) {
//        for (User user : userList) {
//            if (user.getId() == nextId) {
//                return user;
//            }
//        }
//        return null;
        return userList.stream().filter(user -> user.getId() == nextId).findFirst();
    }

    // update user :
    public boolean updateUser(Long nextId, User update) {
        return userList.stream().filter(user -> user.getId() == nextId).findFirst()
                .map(existingUser -> {
                    existingUser.setFirstName(update.getFirstName());
                    existingUser.setLastName(update.getLastName());
                    return true;
                }).orElse(false);
    }
}

// in service we dont want request body as user will be provided by the controller
