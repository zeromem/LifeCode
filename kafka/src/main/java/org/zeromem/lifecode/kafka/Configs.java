package org.zeromem.lifecode.kafka;


import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigParseOptions;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zeromem on 2017/9/7.
 */
public class Configs {
	public static final Config config = ConfigFactory.load();
	public static final Map<String, Object> kafkaConfig = new HashMap<>();

	static {
		kafkaConfig.put("auto.offset.reset", config.getString("kafka.auto.offset.reset"));
		kafkaConfig.put("enable.auto.commit", config.getBoolean("kafka.enable.auto.commit")); // 手动提交offset
		kafkaConfig.put("max.poll.records", config.getInt("kafka.max.poll.records"));
		kafkaConfig.put("bootstrap.servers", config.getString("kafka.bootstrap.servers"));
		kafkaConfig.put("group.id", config.getString("kafka.group.id")); // group.id不要变
		kafkaConfig.put("key.serializer", config.getString("kafka.key.serializer"));
		kafkaConfig.put("value.serializer", config.getString("kafka.value.serializer"));
		kafkaConfig.put("key.deserializer", config.getString("kafka.key.deserializer"));
		kafkaConfig.put("value.deserializer", config.getString("kafka.value.deserializer"));
	}


	public static void main(String[] args) {
        System.out.println(int[].class.getCanonicalName());
        System.out.println(int[].class.getName());
        System.out.println(int[].class.getSimpleName());

//		System.out.println(StringDeserializer.class.getName());
		testLoad();
	}

	public static void testLoad() {
		ConfigParseOptions options = ConfigParseOptions.defaults().setAllowMissing(true);
		System.out.println(ConfigFactory.load(options).getConfig("kafka"));
	}
}
