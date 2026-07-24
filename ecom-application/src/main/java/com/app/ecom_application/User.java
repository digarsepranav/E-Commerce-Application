package com.app.ecom_application;

import lombok.Data;

@Data
public class User {
    private long id;
    private String firstName;
    private String lastName;
}
// this annotation creates getters and setters by itself
