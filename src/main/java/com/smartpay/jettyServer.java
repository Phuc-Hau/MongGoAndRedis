package com.smartpay;

import com.smartpay.model.User;
import com.smartpay.process.impl.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Protocol;
import redis.clients.jedis.UnifiedJedis;
import redis.clients.jedis.providers.PooledConnectionProvider;

import java.util.List;

public class jettyServer {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);


        ServletContextHandler context = new ServletContextHandler();
        context.addServlet(CreateUserProcess.class,"/api/createuser");
        context.addServlet(UpdateUserProcess.class,"/api/updateuser");
        context.addServlet(ListUserProcess.class,"/api/listuser");
        context.addServlet(DeleteUserProcess.class,"/api/deleteuser");
        context.addServlet(GetUserProcess.class,"/api/getuser");

        server.setHandler(context);
        server.start();

        System.out.println("Server started!");
        server.join();
    }
}
