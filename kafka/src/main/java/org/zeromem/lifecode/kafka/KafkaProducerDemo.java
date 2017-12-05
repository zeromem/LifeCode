package org.zeromem.lifecode.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by zeromem on 2017/3/7.
 * 测试用的kafka producer，用于向指定topic发送消息
 * 2017-02-14 17:17:07,WiBeacon,20:28:18:A1:DC:9B,77,2017-02-14 17:18:51,20:28:18:A1:DC:86,1,2412
 */
public class KafkaProducerDemo {
	public static final String servers = "192.168.200.101:9092";
	public static final String serializer = "org.apache.kafka.common.serialization.StringSerializer";
	public static final Producer<String, String> producer;
	public static final String wifi = "20:28:18:A1:DB:9A";
	public static final Random random = new Random();

	static {
		Properties props = new Properties();
		props.put("bootstrap.servers", servers);
		props.put("acks", "-1");
		props.put("retries", 0);
		props.put("linger.ms", 2);
		props.put("key.serializer", serializer);
		props.put("value.serializer", serializer);
		producer = new KafkaProducer<>(props);
	}

	public static void main(String[] args) throws InterruptedException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (int i = 0; i < 1000; i++) {
			String time = format.format(new Date());
			String record = time + ",WiBeacon," + random.nextInt() + "," + random.nextInt(100) + "," + time + "," + wifi + ",1,2412";
			System.out.println(record);
			producer.send(new ProducerRecord<String, String>(
					"test",
//					Integer.toString(i),
					record
			));
			System.out.println("I sent a message to kafka!");
			Thread.sleep(TimeUnit.SECONDS.toMillis(10));
			producer.close();
			System.exit(1);
		}
		producer.close();
	}
}
