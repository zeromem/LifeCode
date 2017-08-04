package org.zeromem.lifecode.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by zeromem on 2017/8/4.
 */
public class GroupConsumeDemo {
	public static final List<String> topics = Arrays.asList("port9000", "port9001", "port9002");
	public static final List<TopicPartition> partitions = Arrays.asList(
			new TopicPartition("port9000", 0),
			new TopicPartition("port9001", 0),
			new TopicPartition("port9001", 1),
			new TopicPartition("port9002", 0)
	);

	public static final KafkaConsumer<String, String> consumer;

	static {
		Properties props = new Properties();
		props.put("auto.offset.reset", "latest");
		props.put("enable.auto.commit", false); // 手动提交offset
		props.put("max.poll.records", 5000);
		props.put("bootstrap.servers", "10.9.59.138:9092");
		props.put("group.id", "lifecode-kafka-consumer"); // group.id不要变
		props.put("key.deserializer", StringDeserializer.class.getName());
		props.put("value.deserializer", StringDeserializer.class.getName());
		consumer = new KafkaConsumer<>(props);
	}

	public static void main(String[] args) {
		// get each partition's beginning offset.
		// if some partition is not exist, it will block forever..
		Map<TopicPartition, Long> beginningOffsets = consumer.beginningOffsets(partitions);
		System.out.println("beginning: " + beginningOffsets);

		// get each partition's next start offset.(committed by consumer group, keep in topic[__consumer_offsets])
		// committed() may return null.
		Map<TopicPartition, Long> committedOffsets = partitions.stream().collect(Collectors.toMap(
				partition -> partition, // partition as key
				partition -> Optional.ofNullable(consumer.committed(partition))
						.map(OffsetAndMetadata::offset).orElse(0L)
		));
		System.out.println("committed: " + committedOffsets);

		// get each partition's end offset.
		Map<TopicPartition, Long> endOffsets = consumer.endOffsets(partitions);
		System.out.println("end: " + endOffsets);

		// subscribe() will subscribe all partitions in those topics.
		consumer.subscribe(topics);
		// cancel subscribe
		consumer.unsubscribe();

		// assign target partitions.
		consumer.assign(partitions);
		// use unsubscribe() will clear those partitions.
		// consumer.unsubscribe();

		// seek a good offset.
		partitions.forEach(topicPartition -> {
			Long begin = beginningOffsets.get(topicPartition);
			Long committed = committedOffsets.get(topicPartition);
			Long end = endOffsets.get(topicPartition);

			consumer.seek(topicPartition, end - 10 > committed ? end - 10 : committed);
		});

		// consume records.
		while (true) {
			// kafka server will do rebalance among consumers in the same group.
			// if one consumer exit from the group, all subsequent records of its assigned partitions will send to other consumers.
			ConsumerRecords<String, String> records = consumer.poll(1000);
			records.forEach(System.out::println);

			// commit offset.
			consumer.commitSync();
		}
	}
}
