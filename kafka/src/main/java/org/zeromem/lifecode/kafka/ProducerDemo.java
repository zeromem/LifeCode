package org.zeromem.lifecode.kafka;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.clients.producer.Producer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author user
 * @date 2017/3/27
 */
public class ProducerDemo {

    public static final String servers = "192.168.200.23:9092";
    public static final String serializer = "org.apache.kafka.common.serialization.StringSerializer";
    public static final Producer<String, String> producer;
    public static final Random random = new Random();

    static {
        Properties props = new Properties();
        props.put("bootstrap.servers", servers);
        props.put("acks", "-1");
        props.put("retries", 0);
        props.put("linger.ms", 2);
        props.put("key.serializer", serializer);
        props.put("value.serializer", serializer);
        producer = new KafkaProducer<String, String>(props);
    }

    public static void main(String[] args) throws InterruptedException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < 10; i++) {
            String time = format.format(new Date());
            final String record = time + "  " + random.nextInt();
            System.out.println(record);
            producer.send(new ProducerRecord<String, String>(
                    "test",
                    record
            ), new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    System.out.println(recordMetadata.offset() + "  " + recordMetadata.partition());
                }
            });
            Thread.sleep(TimeUnit.SECONDS.toMillis(1));
        }
        producer.close();


    }

}
