package com.main.expensemanagerapi.new_domain;

import com.main.expensemanagerapi.vo.UserVo;

import java.util.List;

public class Organization {

    private String id;

    private List<UserVo> users;

    Organization(String id, List<UserVo> users) {
        this.id = id;
        this.users = users;
    }
}
