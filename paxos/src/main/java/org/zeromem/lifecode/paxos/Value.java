package org.zeromem.lifecode.paxos;

import java.io.Serializable;

/**
 * Created by zeromem on 2017/10/9.
 */
public class Value implements Serializable {
	public final Object value;

	private Value(Object value) {
		this.value = value;
	}

	public static Value of(Object value) {
		return new Value(value);
	}
}
