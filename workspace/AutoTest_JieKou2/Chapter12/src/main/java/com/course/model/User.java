package com.course.model;

import lombok.Data;

@Data
public class User {

    int id;
    String userName;
    String password;
    String age;
    String sex;
    String permission;
    String isDelete;

    public String toString(){
        return (
                "{id"+id+","+
                        "userName"+userName+","+
                        "password"+password+","+
                        "age"+age+","+
                        "sex"+sex+","+
                        "permission"+permission+","+
                        "isDelete"+isDelete+"}"
        );
    }
}
