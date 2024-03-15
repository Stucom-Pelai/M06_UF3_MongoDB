package com.mongodb;

import org.bson.Document;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import org.bson.Document;

public class CRUD{
	
	MongoCollection<Document> collection;

	public CRUD() {
		String uri = "mongodb://localhost:27017";
        MongoClientURI mongoClientURI = new MongoClientURI(uri);
        MongoClient mongoClient = new MongoClient(mongoClientURI);

        MongoDatabase mongoDatabase = mongoClient.getDatabase("CrudDB");
        collection = mongoDatabase.getCollection("test");
	}

	/**
	 * 
	 */
	public void create() {
		Document document = new Document().append("firstName", "Upanshu").append("lastName", "Chaudhary")
				.append("age", "22").append("gender", "Male").append("designation", "Software Consultant");
		collection.insertOne(document);
	}

	/**
	 * 
	 */
	public void read() {
		Document document = collection.find().first();
		System.out.println(document.toJson());
	}

	/**
	 * 
	 */
	public void update() {
		UpdateResult result = collection.updateOne(eq("firstName", "Upanshu"), combine(set("age", "23"), set("lastName", "Singh")));		
		System.out.println(result.toString());
	}

	/**
	 * 
	 */
	public void delete() {
		DeleteResult result = collection.deleteMany(eq("age", "22"));
		System.out.println(result.toString());
		
	}

}
