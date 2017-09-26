package org.zeromem.lifecode.mongo.spark;

import com.mongodb.spark.MongoSpark;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.bson.Document;

import java.util.Collections;

/**
 * Created by zeromem on 2017/8/18.
 * 用spark将数据存入mongo
 */
public class SparkWriteToMongo {
	public static void main(String[] args) {
		// conf必须设置spark.mongodb.output.uri
		SparkConf conf = new SparkConf()
				.setAppName("test")
				.setMaster("local")
				.set("spark.mongodb.input.uri", "mongodb://10.9.59.138/flowinsight.wifi_collection")
				.set("spark.mongodb.output.uri", "mongodb://10.9.59.138/flowinsight.wifi_collection");
		JavaSparkContext jsc = new JavaSparkContext(conf);

		// 准备Document
		Document doc = new Document();
		doc.put("ha", "hehe");
		// 如果字段是数组，必须是java的List类型(不能是数组或scala的List)
		doc.put("wa", Collections.singletonList("yes"));
		JavaRDD<Document> rdd = jsc.parallelize(Collections.singletonList(doc));

		MongoSpark.save(rdd);
		// context stop.
		jsc.stop();
	}
}
