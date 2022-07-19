package com.smartpay.process.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartpay.model.Ero;
import com.smartpay.model.User;
import com.smartpay.process.BaseServlet;
import com.smartpay.utils.JsonUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.UnifiedJedis;
import redis.clients.jedis.search.Query;

import java.io.IOException;
import java.util.Map;

public class CreateUserProcess extends BaseServlet {

    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // TODO body
        String body = parserBody(req);
        User user = JsonUtils.toJson(body,User.class);

        if(userDao.insertOne(user)){
            code = Ero.InsertUserSuccessful.name();
            connectRedis.createJedis(user);
        } else{
            code = Ero.InsertUserFail.name();
        }

        // TODO out
        return user;
    }
}
