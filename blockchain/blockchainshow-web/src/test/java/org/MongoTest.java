package org;

import com.mongodb.client.model.Projections;
import org.bson.Document;

import static org.zeromem.lifecode.blockchain.web.util.Constants.mongoCollection;

/**
 * Created by zeromem on 2017/9/5.
 */
public class MongoTest {
	public static void main(String[] args) {
		Document filter = new Document();
		filter.put("nickname", "zeromem");

		Document proj = new Document();
		proj.put("nickname", 1);
		proj.put("_id", -1);

//		mongoCollection.find(filter).first() == null
		System.out.println(mongoCollection.find(filter).projection(Projections.include("nickname")).first());
	}
}
