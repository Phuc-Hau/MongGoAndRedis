package com.smartpay.process.impl;

import com.smartpay.model.User;
import com.smartpay.process.BaseServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class ListUserProcess extends BaseServlet {
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<User> userList = userDao.findAll();
        code = String.valueOf(userList.size());
        return userList;
    }
}
