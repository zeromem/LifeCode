package org.zeromem.lifecode.paxos;

import org.zeromem.lifecode.paxos.message.Message;


/**
 *
 * @author zeromem
 * @date 2017/10/9
 */
public class Value extends Message {
	public final Object value;
	public static final Value NULL = new Value(null);

	private Value(Object value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Value{" +
				"value=" + value +
				'}';
	}

	public static Value of(Object value) {
		return new Value(value);
	}

    public boolean isNull() {
        return value == null;
    }
}
