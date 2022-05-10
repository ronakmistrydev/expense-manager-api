package com.main.expensemanagerapi.domain;

import com.main.expensemanagerapi.vo.RecordUserVo;
import com.main.expensemanagerapi.vo.UserVo;

import java.util.HashMap;
import java.util.List;

public class Organization {

    private final String id;
    private UserVo owner;
    private HashMap<String, UserVo> users;
    private HashMap<String, RecordUserVo> recordUsers;

    public Organization(String id, String userSub) {
        this.id = id;
        this.owner = new UserVo(userSub);
        this.users = new HashMap<>(){{
            put(userSub, new UserVo(userSub));
        }};
        this.recordUsers = new HashMap<>();
    }

    public Organization(String id, UserVo owner, List<UserVo> users) {
        this.id = id;
        this.owner = owner;
        this.users = new HashMap<>();
        users.forEach((user) -> this.users.put(user.getSub(), user));
        this.recordUsers = new HashMap<>();
    }

    public String getId() {
        return this.id;
    }

    public HashMap<String, UserVo> getUsers() {
        return this.users;
    }

    public UserVo getOwner() {
        return owner;
    }
}
