package cn.com.ctbri.ctbigdata.smarteyes.controller;

import com.mongodb.DB;
import com.mongodb.Mongo;

import java.util.Set;

public class MongodbConnection {
	public static void main(String[] args) {
		try {
			// 连接到 mongodb 服务
			Mongo mongo = new Mongo("192.168.200.82", 27017);
			//根据mongodb数据库的名称获取mongodb对象 ,
			DB db = mongo.getDB("xinjiangBranch");
			Set<String> collectionNames = db.getCollectionNames();
			// 打印出test中的集合
			for (String name : collectionNames) {
				System.out.println("collectionName===" + name);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
