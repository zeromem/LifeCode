package org.zeromem.lifecode.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import static org.zeromem.lifecode.kafka.Configs.kafkaConfig;

/**
 * Created by zeromem on 2017/9/8.
 */
public class SimpleProduce {
	public static void main(String[] args) {
		KafkaProducer<String, String> producer = new KafkaProducer<>(kafkaConfig);
		producer.send(new ProducerRecord<>("test", "hello"));
		System.out.println("step1");
		producer.send(new ProducerRecord<>("replica", "world"));
		System.out.println("step2");
	}
}
