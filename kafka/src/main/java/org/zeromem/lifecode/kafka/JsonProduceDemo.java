package org.zeromem.lifecode.kafka;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.Serializer;

import java.util.*;
import java.util.concurrent.ExecutionException;

import static org.zeromem.lifecode.kafka.Configs.kafkaConfig;

/**
 * Created by zeromem on 2017/9/21.
 */
public class JsonProduceDemo {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		// 创建producer
		HashMap<String, Object> config = new HashMap<>(kafkaConfig);
		config.put("value.serializer", "org.zeromem.lifecode.kafka.JsonProduceDemo$PersonJsonSer");
		KafkaProducer<String, Person> producer = new KafkaProducer<>(config);

		for (int age = 20; age < 35; age++) {
			Person person = new Person("zero", age);
			person.setInfo(String.valueOf(new Random().nextDouble()));

			// 用实例生成一条ProducerRecord
			ProducerRecord<String, Person> data =
                    new ProducerRecord<>("test", person.name, person);
            System.out.println(person);

			// 发送
			producer.send(data).get();
		}
	}

	public static class PersonJsonSer implements Serializer<Person> {

		@Override
		public void configure(Map<String, ?> configs, boolean isKey) {

		}

		@Override
		public byte[] serialize(String topic, Person data) {
			return JSON.toJSONBytes(data);
		}

		@Override
		public void close() {

		}
	}
}

