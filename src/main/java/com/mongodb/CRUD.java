package com.mongodb;

import org.bson.Document;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import org.bson.Document;

public class CRUD {

	MongoCollection<Document> collection;
	ObjectId id;

	public CRUD() {
		String uri = "mongodb://localhost:27017";
		MongoClientURI mongoClientURI = new MongoClientURI(uri);
		MongoClient mongoClient = new MongoClient(mongoClientURI);

		MongoDatabase mongoDatabase = mongoClient.getDatabase("CrudDB");
		collection = mongoDatabase.getCollection("test");
	}

	/**
	 * create a new document in test collection
	 */
	public void create() {
		Document document = new Document("_id", new ObjectId()).append("firstName", "Carlos").append("lastName", "Lopez")
				.append("age", "42").append("gender", "Male").append("designation", "Programmer");
		collection.insertOne(document);
		System.out.println("document inserted" + document);
		this.id = new ObjectId();
		document = new Document("_id", this.id).append("firstName", "John").append("lastName", "Doe")
				.append("age", "22").append("gender", "Male").append("designation", "Software Consultant");
		collection.insertOne(document);
		System.out.println("document inserted with ID: " + this.id);
	}

	/**
	 * read a document from test collection
	 */
	public void read() {
		// read document by position
		Document document = collection.find().first();
		System.out.println("document read by position " + document.toJson());
		
		// read document by id
		document = collection.find(eq("_id", this.id)).first();
		System.out.println("document read by id " + document.toJson());
		
		// read n documents
		// Read the first '5' documents
	    Iterable<Document> documents = collection.find().limit(5);

	    int count = 0;
	    for (Document doc : documents) {
	        System.out.println("document read on list " + (++count) + ": " + doc.toJson());
	    }
	}

	/**
	 * update a document from test collection
	 */
	public void update() {
		UpdateResult result = collection.updateOne(eq("firstName", "John"),
				combine(set("age", "23"), set("lastName", "Smith")));
		System.out.println("document updated by name" + result.toString());
	}

	/**
	 * delete a document from test collection
	 */
	public void delete() {
		DeleteResult result = collection.deleteMany(eq("age", "42"));
		System.out.println("document deleted by name" + result.toString());

	}

}
