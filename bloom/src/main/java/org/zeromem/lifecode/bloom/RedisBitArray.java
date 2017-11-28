package org.zeromem.lifecode.bloom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import static org.zeromem.study.redis.DemoJedisPool.jedisPool;

/**
 * Created by zeromem on 2017/5/4.
 * 借助redis实现的BitArray
 */
public class RedisBitArray {
	private static final Logger log = LoggerFactory.getLogger(RedisBitArray.class);
	private final Jedis jedis = jedisPool.getResource();
	private String redisKey;

	public RedisBitArray(String key, int numBits) {
		int i = 0;
		for (; i < 10 && jedis.exists(key); i++) {
			log.error("key exist! " + key);
			key = key + "-new";
		}
		if (i == 10) {
			log.error("Can't create a unique bit array!");
			return;
		}
		jedis.setbit(key, numBits - 1, false);
		redisKey = key;
	}

	/** Returns true if the bit changed value. */
	boolean set(long index) {
		return jedis.setbit(redisKey, index - 1, true);
	}

	boolean get(long index) {
		return jedis.getbit(redisKey, index - 1);
	}

	/** Number of bits */
	long bitSize() {
		return jedis.bitcount(redisKey);
	}

	/** Number of set bits (1s) */
	long cardinality() {
		return jedis.bitcount(redisKey);
	}

	public static void main(String[] args) {
		Jedis jedis = jedisPool.getResource();
		for (String s : jedis.keys("*")) {
			System.out.println(s);
		}
	}
}
