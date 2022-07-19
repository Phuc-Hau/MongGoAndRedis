package com.smartpay.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.smartpay.model.User;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class UserDao extends Dao<User>{

    private  final String collection ="Users";

    @Override
    public boolean insertOne(User model) {
        // Connect DB
        MongoDatabase db = mongodb.getMongoClient();
        MongoCollection col = db.getCollection(collection);

        // Document insert
        Document document = Document.parse(gson.toJson(model));

        User user = new User();
        user.setUsername(model.getUsername());

        // Check the Username
        if(findByKey(user).size()==0){
            col.insertOne(document);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void update(User model) {
        MongoDatabase db = mongodb.getMongoClient();
        MongoCollection col = db.getCollection(collection);

        BasicDBObject newDocument = new BasicDBObject();
        newDocument.put("username", model.getUsername());

        Document updateDoc = Document.parse(gson.toJson(model));

        col.updateOne(newDocument, new Document("$set", updateDoc));
    }

    @Override
    public void delete(User model) {
        MongoDatabase db = mongodb.getMongoClient();
        MongoCollection col = db.getCollection(collection);
        Document doc = Document.parse(gson.toJson(model));
        col.deleteOne(doc);
    }

    @Override
    public List findByKey(User model) {
        MongoDatabase db = mongodb.getMongoClient();
        MongoCollection col = db.getCollection(collection);
        Document doc = Document.parse(gson.toJson(model));
        FindIterable<Document> result = col.find(doc);
        List<User> userList = new ArrayList<>();

        for (Document  docs : result) {
            userList.add(gson.fromJson(docs.toJson(), User.class));
        }
        return userList;
    }

    @Override
    public List findAll() {
        return findByKey(new User());
    }
}
