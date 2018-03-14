package org.zeromem.lifecode.kafka.streams;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.zeromem.lifecode.kafka.Person;

import java.util.Arrays;

/**
 * Created by zeromem on 2017/8/4.
 */
public class KafkaStreamsDemo {
	public static void main(String[] args) {
		// TODO: 2018/3/14 write a demo.
		Person person = new Person("world", 29);
		person.setInfo("hello world");

		byte[] bytes = JSON.toJSONBytes(person, SerializerFeature.UseSingleQuotes);
        System.out.println(JSON.toJSONString(person, SerializerFeature.UseSingleQuotes));
        System.out.println(Arrays.toString(bytes));

	}
}
