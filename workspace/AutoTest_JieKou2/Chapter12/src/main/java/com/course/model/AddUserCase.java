package com.course.model;

import lombok.Data;

@Data
public class AddUserCase {
    int id;
    String userName;
    String password;
    String sex;
    String age;
    String permission;
    String isDelete;
    String expected;

}
