package org.zeromem.lifecode.kafka.streams.thrift;

import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.state.QueryableStoreType;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.apache.thrift.TException;

/**
 * @author zeromem
 * @date 2018/3/15
 */
public class StreamsWordCountServiceHandler implements StreamsWordCountService.Iface {
    public static final String STORE_NAME = "store-talk-word-count";
    KafkaStreams streams;

    public StreamsWordCountServiceHandler(KafkaStreams streams) {
        this.streams = streams;
    }

    @Override
    public long get(String word) throws TException {
        ReadOnlyKeyValueStore<String, Long> store = streams.store(STORE_NAME, QueryableStoreTypes.<String, Long>keyValueStore());
        if (store == null) {
            return Long.MIN_VALUE;
        }
        Long aLong = store.get(word);
        return aLong == null ? -1 : aLong;
    }
}
