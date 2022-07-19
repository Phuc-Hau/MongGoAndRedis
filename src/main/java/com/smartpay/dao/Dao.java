package com.smartpay.dao;

import com.google.gson.Gson;
import com.smartpay.utils.ConnectMongoDB;

import java.util.List;

public abstract class Dao<E> {

    ConnectMongoDB mongodb = new ConnectMongoDB();
    Gson gson = new Gson();

    public abstract boolean insertOne(E model);

    public abstract void update(E model);

    public abstract void delete(E model);

    public abstract List findByKey(E model);

    public abstract List findAll();
}
