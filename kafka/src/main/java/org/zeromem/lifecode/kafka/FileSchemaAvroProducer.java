package org.zeromem.lifecode.kafka;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.zeromem.lifecode.kafka.Configs.kafkaConfig;

/**
 * Created by zeromem on 2017/9/21.
 */
public class FileSchemaAvroProducer {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		// 创建producer
		HashMap<String, Object> config = new HashMap<>(kafkaConfig);
		KafkaProducer<String, String> producer = new KafkaProducer<>(config);

		for (int id = 0; id < 10; id++) {
			String name = "Cus" + id;

			// 使用GenericRecord 生成实例
            JSONObject customer = new JSONObject();
            customer.put("id", id);
			customer.put("name", name);

			if (id % 2 == 0) {
				List<String> history = new LinkedList<>();
				history.add(id + "A");
				history.add(id + "B");
				history.add(id + "C");
				customer.put("history", history);
			}

			System.out.println(customer);

			// 用实例生成一条ProducerRecord
			ProducerRecord<String, String> data = new ProducerRecord<>("test", customer.toJSONString());

			// 发送
			producer.send(data, (meta, exception) -> {
                if (exception != null) {
                    exception.printStackTrace();
                }
                System.out.println("meta: " + meta);
            });
		}
		producer.close();
	}
}


