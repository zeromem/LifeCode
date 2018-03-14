package org.zeromem.lifecode.kafka;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

import java.util.*;

import static org.zeromem.lifecode.kafka.Configs.kafkaConfig;

/**
 * Created by zeromem on 2017/9/21.
 */
public class JsonConsumeDemo {
	public static void main(String[] args) {
		HashMap<String, Object> config = new HashMap<>(kafkaConfig);
		config.put("value.deserializer", "org.zeromem.lifecode.kafka.JsonConsumeDemo$PersonJsonDeser");
		KafkaConsumer<String, Person> consumer = new KafkaConsumer<>(config);

		Set<String> topics = Collections.singleton("test");
		consumer.subscribe(topics);
		while (true) {

			ConsumerRecords<String, Person> records = consumer.poll(300);
			for (ConsumerRecord<String, Person> record : records) {
                System.out.print("woo - ");
				System.out.println(record.value());
			}
			consumer.commitSync();
		}

	}

	public static class PersonJsonDeser implements Deserializer<Person> {

		@Override
		public void configure(Map<String, ?> configs, boolean isKey) {

		}

		@Override
		public Person deserialize(String topic, byte[] data) {
			return JSON.parseObject(data, Person.class);
		}

		@Override
		public void close() {

		}
	}
}
