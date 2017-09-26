package org.zeromem.lifecode.kafka;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.zeromem.lifecode.kafka.Configs.kafkaConfig;

/**
 * Created by zeromem on 2017/9/21.
 */
public class JsonConsumeDemo {
	public static void main(String[] args) {
		HashMap<String, Object> config = new HashMap<>(kafkaConfig);
		config.put("value.deserializer", "org.zeromem.lifecode.kafka.JsonConsumeDemo$JsonDeserializer");
		KafkaConsumer<String, JSONObject> consumer = new KafkaConsumer<>(config);

		Set<String> topics = Collections.singleton("customer");
		consumer.subscribe(topics);
		while (true) {

			ConsumerRecords<String, JSONObject> records = consumer.poll(30000);
			for (ConsumerRecord<String, JSONObject> record : records) {
				System.out.println(record.value());
			}
		}

	}

	public static class JsonDeserializer implements Deserializer<JSONObject> {

		@Override
		public void configure(Map<String, ?> configs, boolean isKey) {

		}

		@Override
		public JSONObject deserialize(String topic, byte[] data) {
			return JSON.parseObject(data, JSONObject.class);
		}

		@Override
		public void close() {

		}
	}
}
