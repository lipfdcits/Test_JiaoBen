package com.course.model;

import lombok.Data;

@Data
public class LoginCase {
    int id;
    String userName;
    String password;
    String expected;
}
