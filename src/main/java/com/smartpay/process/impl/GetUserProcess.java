package com.smartpay.process.impl;

import com.smartpay.model.Ero;
import com.smartpay.model.User;
import com.smartpay.process.BaseServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class GetUserProcess extends BaseServlet {
    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //TODO body
        String username = req.getParameter("username");

        User userKey = new User();
        userKey.setUsername(username);

        User user = connectRedis.getUserJedis(userKey);

        if(user == null){
            List<User> userList = userDao.findByKey(userKey);
            if(userList.size()>0){
                user = userList.get(0);
                code = Ero.GetUserSuccessful.name();
                connectRedis.createJedis(user);
            }else{
                code = Ero.GetUserFail.name();
                userList.add(user);
            }
        }else {
            code = Ero.GetUserSuccessful.name();
        }

        return user;
    }
}
