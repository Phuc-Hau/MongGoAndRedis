package com.smartpay.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartpay.model.User;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Map;

public class ConnectRedis {
    private static long STT=60;

    public User getUserJedis(User user){
        Jedis client =getJedis();
        String id = getId(user);
        Map<String, String> userjedis = client.hgetAll(id);
        if(!userjedis.isEmpty()){
            user.setUsername(userjedis.get("username"));
            user.setFullname(userjedis.get("fullname"));
            user.setPassword(userjedis.get("password"));
        }else {
            return null;
        }
        return user;
    }

    public static Jedis getJedis(){
//        JedisPool pool = new JedisPool("redis-17327.c54.ap-northeast-1-2.ec2.cloud.redislabs.com", 17327);
//        Jedis jedis = pool.getResource();
//        jedis.auth("JbMz74alUsd5pRNosY00DZJiGtsI6o9r");
        JedisPool pool = new JedisPool("localhost", 6379);
        Jedis jedis = pool.getResource();
        return jedis;
    }

    public static void updateJedis(User user){
        ObjectMapper mapObject = new ObjectMapper();
        Map< String, String > mapObj = mapObject.convertValue(user, Map.class);
        mapObj.remove("id");

        Jedis client =getJedis();
        String id = getId(user);

        if(!id.equals("")){
            client.hmset(id,mapObj);
//            client.expire(id,STT);
        }

    }

    public static void createJedis(User user){
        Jedis client =getJedis();
        ObjectMapper mapObject = new ObjectMapper();
        Map< String, String > mapObj = mapObject.convertValue(user, Map.class);
        mapObj.remove("id");

        String id = user.getUsername();
        client.hmset("user:"+id, mapObj);
        try{
            client.srem(user.getUsername());
        } catch (Exception e){}
        client.sadd(user.getUsername(),"user:"+id);
//        client.expire("user:"+id,STT);
    }

    public static void deleteJedis(User user){
        Jedis client =getJedis();
        String id = getId(user);
        try{
            client.del(id);
        }catch (Exception e){}

        try{
            client.srem(user.getUsername());
        }catch (Exception e){}

    }

    public static String getId(User user){
        Jedis client =getJedis();
        String id = String.valueOf(client.smembers(user.getUsername()));
        id = id.substring(1).replace("]","");
        return id;
    }

}
