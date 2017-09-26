package org.zeromem.lifecode.kafka;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;

import java.util.List;
import java.util.Map;

import static org.zeromem.lifecode.kafka.Configs.kafkaConfig;

/**
 * Created by zeromem on 2017/9/8.
 */
public class TopicInfo {
	public static void main(String[] args) {
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(kafkaConfig);
		Map<String, List<PartitionInfo>> topicInfos = consumer.listTopics();
		System.out.println(topicInfos);
	}
}
