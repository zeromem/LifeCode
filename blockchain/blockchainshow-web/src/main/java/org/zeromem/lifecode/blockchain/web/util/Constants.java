package org.zeromem.lifecode.blockchain.web.util;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.bson.Document;

/**
 * Created by zeromem on 2017/9/5.
 */
public class Constants {
	public static final Config config = ConfigFactory.load("mongo.conf");
	public static final MongoCollection<Document> mongoCollection;
	static {
		MongoClient mongoClient = new MongoClient(
				config.getString("mongo.host"),
				config.getInt("mongo.port")
		);
		MongoDatabase database = mongoClient.getDatabase(config.getString("mongo.database"));
		mongoCollection = database.getCollection(config.getString("mongo.collection"));
	}

	public static void main(String[] args) {
		mongoCollection.insertOne(new Document("key", "value"));
		for (Document document : mongoCollection.find()) {
			System.out.println(document);
		}
	}
}
