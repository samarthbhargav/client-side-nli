package com.nanobi.client.dao;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.nanobi.client.constants.UserDaoConstants;


public class UserDao
{
    private DBCollection collection;


    public UserDao() throws UnknownHostException
    {
        MongoClient client = new MongoClient();
        collection = client.getDB( UserDaoConstants.DB_NAME ).getCollection( UserDaoConstants.COLLECTION_NAME );
    }


    public boolean authenticate( String user, String password )
    {
        BasicDBObject object = new BasicDBObject();
        object.append( UserDaoConstants.FIELD_USER_NAME, user );

        try {
            DBObject u = collection.findOne( object );
            if ( u != null ) {
                String pass = (String) u.get( UserDaoConstants.FIELD_PASSWORD );
                if ( pass.equals( password ) ) {
                    return true;
                }
            }
        } catch ( Exception e ) {
            System.out.println( e.getMessage() );
            return false;
        }

        return false;
    }
    
    public void addUser(String user, String password) {
        BasicDBObject toAdd = new BasicDBObject();
        toAdd.append( UserDaoConstants.FIELD_USER_NAME, user );
        toAdd.append( UserDaoConstants.FIELD_PASSWORD, password );
        collection.insert( toAdd );
    }
}
