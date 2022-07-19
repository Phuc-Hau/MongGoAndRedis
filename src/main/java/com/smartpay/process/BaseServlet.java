package com.smartpay.process;

import com.google.gson.Gson;
import com.smartpay.dao.UserDao;
import com.smartpay.model.ResponseData;
import com.smartpay.utils.ConnectRedis;
import com.smartpay.utils.JsonUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class BaseServlet extends HttpServlet {

    public String code;
    public UserDao userDao = new UserDao();
    public ConnectRedis connectRedis = new ConnectRedis();


    public abstract Object process(HttpServletRequest req, HttpServletResponse resp) throws IOException;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Object data = process(req,resp);
        ResponseData responseData = new ResponseData();
        responseData.setCode(code);
        if(data != null){
            responseData.setData(data);
        }
        out(resp, JsonUtils.toString(responseData));

    }

    protected String parserBody(HttpServletRequest req) throws IOException {
        BufferedReader br = req.getReader();
        StringBuffer sb =new StringBuffer();
        String tym;
        while ((tym = br.readLine()) != null){
            sb.append(tym);
        }
        return sb.toString();
    }

    private void out(HttpServletResponse resp,String body) throws IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_OK);

        PrintWriter out = resp.getWriter();
        out.print(body);
        out.flush();
    }
}
