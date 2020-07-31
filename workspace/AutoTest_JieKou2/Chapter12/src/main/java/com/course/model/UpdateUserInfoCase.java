package com.course.model;

import lombok.Data;

@Data
public class UpdateUserInfoCase {
    int id;
    int userId;
    String userName;
    String sex;
    String age;
    String permission;
    String isDelete;
    String expected;
}
