package com.smartpay.utils;

import com.mongodb.client.*;

public class ConnectMongoDB {
    private static final String connectDB ="mongodb+srv://phuchau:1901@cluster0.97zled3.mongodb.net/?retryWrites=true&w=majority";
    private static final String db_Name ="SmartPay";

    public static MongoDatabase getMongoClient(){
        MongoClient client = MongoClients.create(connectDB);
        MongoDatabase  db = client.getDatabase(db_Name);
        return db;
    }
}
