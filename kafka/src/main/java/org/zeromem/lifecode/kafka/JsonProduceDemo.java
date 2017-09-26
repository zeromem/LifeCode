package org.zeromem.lifecode.kafka;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.typesafe.config.ConfigFactory;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.Serializer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static org.zeromem.lifecode.kafka.Configs.kafkaConfig;

/**
 * Created by zeromem on 2017/9/21.
 */
public class JsonProduceDemo {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		// 创建producer
		HashMap<String, Object> config = new HashMap<>(kafkaConfig);
		config.put("value.serializer", "org.zeromem.lifecode.kafka.JsonProduceDemo$JsonSerializer");
		KafkaProducer<String, JSONObject> producer = new KafkaProducer<>(config);

		for (int id = 0; id < 10; id++) {
			JSONObject object = new JSONObject();
			String name = "Customer" + id;

			// 生成json实例
			object.put("id", id);
			object.put("name", name);

			if (id % 2 == 0) {
				List<String> history = new LinkedList<>();
				history.add(id + "O");
				history.add(id + "P");
				history.add(id + "Q");
				object.put("history", history);
			}

			System.out.println(object);

			// 用实例生成一条ProducerRecord
			ProducerRecord<String, JSONObject> data = new ProducerRecord<>("customer", name, object);

			// 发送
			producer.send(data).get();
		}
	}

	public static class JsonSerializer implements Serializer<JSONObject> {

		@Override
		public void configure(Map<String, ?> configs, boolean isKey) {

		}

		@Override
		public byte[] serialize(String topic, JSONObject data) {
			return JSON.toJSONBytes(data);
		}

		@Override
		public void close() {

		}
	}

}

