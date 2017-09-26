package org.zeromem.lifecode.kafka;

import com.alibaba.fastjson.JSONObject;
import com.typesafe.config.ConfigFactory;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static org.zeromem.lifecode.kafka.Configs.kafkaConfig;

/**
 * Created by zeromem on 2017/9/21.
 */
@Deprecated
public class AvroProduceDemo {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		// 从avro配置文件生成schema
		String schemaString = ConfigFactory.load().getString("avro.schema");
		System.out.println("schema: " + schemaString);
		Schema schema = new Schema.Parser().parse(schemaString);

		// 创建producer
		HashMap<String, Object> config = new HashMap<>(kafkaConfig);
		config.put("key.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");
		config.put("value.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");

		KafkaProducer<String, GenericRecord> producer = new KafkaProducer<>(config);

		for (int id = 0; id < 10; id++) {
			String name = "Cus" + id;

			// 使用GenericRecord 生成实例
			GenericRecord customer = new GenericData.Record(schema);
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
			ProducerRecord<String, GenericRecord> data = new ProducerRecord<>("customer", name, customer);

			// 发送
			producer.send(data).get();
		}
	}
}

class Customer {
	private Integer id;
	private String name;
	private List history;

	public Customer() {

	}
}


