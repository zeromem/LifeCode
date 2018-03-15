package org.zeromem.lifecode.kafka.streams;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.zeromem.lifecode.kafka.streams.thrift.StreamsWordCountRPCServer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 * Created by zeromem on 2017/8/4.
 */
public class KafkaStreamsInstance {
    public static void main(String[] args) throws InterruptedException {
        Integer port = Integer.valueOf(args[0]);
        final String RPC_ENDPOINT = "localhost:" + port;
        // kafka streams properties.
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "talk-word-count");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.APPLICATION_SERVER_CONFIG, RPC_ENDPOINT);

        // create stream topology.
        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, String> source = builder.stream("talk");
        final Pattern pattern = Pattern.compile("\\W+");
        source.flatMapValues(value -> Arrays.asList(pattern.split(value)))
                .map(((key, value) -> new KeyValue<>(value, value)))
                .groupByKey().count(Materialized.as("store-talk-word-count"));
        Topology topology = builder.build();
        // topology.describe();

        // run topology
        KafkaStreams streams = new KafkaStreams(topology, props);
        streams.cleanUp();
        streams.start();


        // start rpc server.
        StreamsWordCountRPCServer.start(streams, port);

        TimeUnit.MINUTES.sleep(10);
        streams.close();
        System.exit(1);
    }
}
