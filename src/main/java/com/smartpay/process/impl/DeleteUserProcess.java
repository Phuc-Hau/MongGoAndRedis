package com.smartpay.process.impl;

import com.smartpay.model.Ero;
import com.smartpay.model.User;
import com.smartpay.process.BaseServlet;
import com.smartpay.utils.JsonUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import redis.clients.jedis.UnifiedJedis;

import java.io.IOException;

public class DeleteUserProcess extends BaseServlet {
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //TODO body
        String body = parserBody(req);

        User user = JsonUtils.toJson(body,User.class);
        userDao.delete(user);
        connectRedis.deleteJedis(user);
        code = Ero.DeleteUserSuccessful.name();

        //TODO out
        return user;
    }
}
