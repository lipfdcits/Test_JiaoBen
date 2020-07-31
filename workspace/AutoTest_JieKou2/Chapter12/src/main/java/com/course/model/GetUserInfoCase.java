package com.course.model;

import lombok.Data;

@Data
public class GetUserInfoCase {
    int id;
    int userId;
    String expected;
}
