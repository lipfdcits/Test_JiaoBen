package com.course.model;

import lombok.Data;

@Data
public class User {
    Integer id;
    String userName;
    String password;
    String age;
    String sex;
    String permission;
    String isDelete;
}
