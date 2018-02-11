package org.zeromem.lifecode.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import static org.zeromem.lifecode.kafka.Configs.kafkaConfig;

/**
 * Created by zeromem on 2017/9/20.
 */
public class AsyncProduce {
	public static void main(String[] args) throws InterruptedException {
		KafkaProducer<String, String> producer = new KafkaProducer<>(kafkaConfig);
		ProducerRecord<String, String> record =
				new ProducerRecord<>("test", 0, "prod-key", "prod-value");

		producer.send(record, (meta, exception) -> {
			if (exception != null) {
				exception.printStackTrace();
			} else {
				System.out.println("checksum: " + meta.checksum());
				System.out.println("timestamp: " + meta.timestamp());
				System.out.println("key size: " + meta.serializedKeySize());
				System.out.println("value size: " + meta.serializedValueSize());
				System.out.println(meta);
			}
		});
		Thread.sleep(10000);
	}
}
