package org.zeromem.lifecode.kafka;

import com.google.common.io.Resources;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * This producer will send a bunch of messages to topic "fast-messages". Every so often,
 * it will send a message to "slow-messages". This shows how messages can be sent to
 * multiple topics. On the receiving end, we will see both kinds of messages but will
 * also see how the two topics aren't really synchronized.
 */
public class Producer {
    public static void main(String[] args) throws IOException {
        // set up the producer
        KafkaProducer<String, String> producer;
        try (InputStream props = Resources.getResource("producer.props").openStream()) {
            Properties properties = new Properties();
            properties.load(props);
            producer = new KafkaProducer<>(properties);
        }

        System.out.println("kafka begin.");
        try {
            for (int i = 1; i < 100; i++) {
                // send lots of messages
                producer.send(new ProducerRecord<String, String>(
                        "fast-messages",
                        String.format("{\"type\":\"test\", \"t\":%.3f, \"k\":%d}", System.nanoTime() * 1e-9, i)));
                System.out.println(String.format("{\"type\":\"test\", \"t\":%.3f, \"k\":%d}", System.nanoTime() * 1e-9, i));
                // every so often send to a different topic
                System.out.println(i);
                if (i % 1000 == 0) {
                    producer.send(new ProducerRecord<String, String>(
                            "fast-messages",
                            String.format("{\"type\":\"marker\", \"t\":%.3f, \"k\":%d}", System.nanoTime() * 1e-9, i)));
                    System.out.println("step1");
                    producer.send(new ProducerRecord<String, String>(
                            "summary-markers",
                            String.format("{\"type\":\"other\", \"t\":%.3f, \"k\":%d}", System.nanoTime() * 1e-9, i)));
                    System.out.println("step2");
                    producer.flush();
                    System.out.println("Sent msg number " + i);
                }
                System.out.println("one produced.");
            }
        } catch (Throwable throwable) {
            System.out.printf("%s", throwable.getStackTrace());
        } finally {
            producer.close();
        }

    }
}
