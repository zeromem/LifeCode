package org.zeromem.lifecode.akka.remote;

import java.io.Serializable;

/**
 * @author zeromem
 * @date 2017/10/18
 */
public class Message implements Serializable {
	public final String value;

	public Message(String msg) {
		this.value = msg;
	}
}
