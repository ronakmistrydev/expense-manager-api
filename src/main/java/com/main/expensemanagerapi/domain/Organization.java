package com.main.expensemanagerapi.domain;

import com.main.expensemanagerapi.vo.TransactionUserVo;
import com.main.expensemanagerapi.vo.UserVo;

import java.util.HashMap;

public class Organization {

    private final String id;

    private HashMap<String, UserVo> users;
    private HashMap<String, TransactionUserVo> transactionUsers;

    public Organization(String id, String userSub) {
        this.id = id;
        this.users = new HashMap<>(){{
            put(userSub, new UserVo(userSub));
        }};
        this.transactionUsers = new HashMap<>();
    }

    public String getId() {
        return this.id;
    }

    public HashMap<String, UserVo> getUsers() {
        return this.users;
    }

    /*
    * add user by sub id
    * add user by userVo
    * update user by sub id with user vo or editable fields
    * */
}
