package com.course.model;

import lombok.Data;

@Data
public class GetUserListCase {
    int id;
    String userName;
    String age;
    String sex;
    String expected;
}
