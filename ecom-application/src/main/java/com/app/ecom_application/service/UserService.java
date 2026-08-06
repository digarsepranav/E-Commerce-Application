package com.app.ecom_application.service;

import com.app.ecom_application.dto.AddressDTO;
import com.app.ecom_application.dto.UserRequest;
import com.app.ecom_application.dto.UserResponse;
import com.app.ecom_application.model.Address;
import com.app.ecom_application.model.User;
import com.app.ecom_application.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }  // Or can use RequiredArgsConstructor

    public List<UserResponse> fetchAllUsers() {
        return userRepository.findAll().stream()
                .map(this::mapToUserResponse)
                .collect(Collectors.toList());
    }

    public void createUser(UserRequest userRequest) {
//        user.setId(nextId++);
        User user = new User();
        updateUserFromRequest(user, userRequest);
        userRepository.save(user);
    }

    private void updateUserFromRequest(User user, UserRequest userRequest) {
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPhoneNo(userRequest.getPhone());

        if (userRequest.getAddress() != null) {
            Address addressDTO = new Address();
            addressDTO.setStreet(userRequest.getAddress().getStreet());
            addressDTO.setCountry(userRequest.getAddress().getCountry());
            addressDTO.setState(userRequest.getAddress().getState());
            addressDTO.setCity(userRequest.getAddress().getCity());
            addressDTO.setZipcode(userRequest.getAddress().getZipcode());
            user.setAddress(addressDTO);
        }
    }

    public Optional<UserResponse> fetchUser(Long nextId) {
//        for (User user : userList) {
//            if (user.getId() == nextId) {
//                return user;
//            }
//        }
//        return null;
//        return userList.stream().filter(user -> user.getId() == nextId).findFirst();
        return userRepository.findById(nextId)
                .map(this::mapToUserResponse);
    }

    // update user :
    public boolean updateUser(Long nextId, UserRequest updateUserRequest) {
//        return userList.stream().filter(user -> user.getId() == nextId).findFirst()
//                .map(existingUser -> {
//                    existingUser.setFirstName(update.getFirstName());
//                    existingUser.setLastName(update.getLastName());
//                    return true;
//                }).orElse(false);
        return userRepository.findById(nextId).map(existingUser -> {
            updateUserFromRequest(existingUser, updateUserRequest);
            userRepository.save(existingUser);
            return true;
        }).orElse(false);
    }


    private UserResponse mapToUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(String.valueOf(user.getId()));
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setEmail(user.getEmail());
        userResponse.setPhone(user.getPhoneNo());
        userResponse.setRole(user.getUserRole());

        if (user.getAddress() != null) {
            AddressDTO addressDTO = new AddressDTO();
            addressDTO.setStreet(user.getAddress().getStreet());
            addressDTO.setCity(user.getAddress().getCity());
            addressDTO.setState(user.getAddress().getState());
            addressDTO.setZipcode(user.getAddress().getZipcode());
            userResponse.setAddress(addressDTO);
        }
        return userResponse;
    }
}

// in service we dont want request body as user will be provided by the controller
