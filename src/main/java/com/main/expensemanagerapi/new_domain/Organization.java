package com.main.expensemanagerapi.new_domain;

import com.main.expensemanagerapi.vo.UserVo;

import java.util.HashMap;
import java.util.List;

public class Organization {

    private final String id;

    private HashMap<String, UserVo> users;

    Organization(String id, String userSub) {
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
