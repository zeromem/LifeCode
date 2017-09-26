package org.zeromem.lifecode.mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;

import org.bson.Document;
import org.bson.conversions.Bson;

import static com.mongodb.client.model.Projections.*;
import static org.zeromem.lifecode.mongo.Configs.config;

/**
 * Created by zeromem on 2017/9/7.
 */
public class SimpleRead {
	public static void main(String[] args) {
		// connect to collection.
		MongoClient client = new MongoClient(config.getString("mongo.host"), config.getInt("mongo.port"));
		MongoDatabase database = client.getDatabase(config.getString("mongo.database"));
		MongoCollection<Document> col = database.getCollection(config.getString("mongo.collections.c1"));
		Runtime.getRuntime().addShutdownHook(new Thread(client::close));

		// construct filter.
		Bson filter = and(
				eq("name", "zeromem"),
				eq("password", "parallel")
		);


		// construct projection.
		Bson proj = fields(
				include("name", "password"),
				excludeId()
		);


		FindIterable<Document> documents = col.find(filter).projection(proj);

		for (Document document : documents) {
			System.out.println("---" + document);
		}
	}
}
