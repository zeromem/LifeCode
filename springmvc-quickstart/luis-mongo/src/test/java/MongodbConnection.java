import com.mongodb.DB;
import com.mongodb.Mongo;
import org.junit.Test;

import java.util.Set;

/**
 * Created by luyi on 2017/6/21.
 */
public class MongodbConnection {
	@Test
	public void testMongodb() {
		try {
			// 连接到 mongodb 服务
			Mongo mongo = new Mongo("192.168.200.82", 27017);
			//根据mongodb数据库的名称获取mongodb对象
			DB db = mongo.getDB("flowinsight");
			Set<String> collectionNames = db.getCollectionNames();
			// 打印出集合
			if(collectionNames.size()!=0){
				for (String name : collectionNames) {
					System.out.println("collectionName===" + name);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
