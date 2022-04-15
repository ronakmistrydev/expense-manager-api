package com.main.expensemanagerapi.domain;

import com.main.expensemanagerapi.vo.UserVo;

import java.util.HashMap;

public class Organization {

    private final String id;

    private HashMap<String, UserVo> users;

    public Organization(String id, String userSub) {
        this.id = id;
        this.users = new HashMap<>(){{
            put(userSub, new UserVo(userSub));
        }};
    }

    /*
    * add user by sub id
    * add user by userVo
    * update user by sub id with user vo or editable fields
    * */
}
