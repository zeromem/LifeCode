package org.zeromem.lifecode.hack;

/**
 * Created by zeromem on 2017/9/19.
 */
public class Short2Bytes {
	public static void main(String[] args) {
		Short a = 10;
//		short a = 10;
		byte byte1 = (byte) (a >>> 8); // 高位
		byte byte2 = (byte) (a & 0xff); // 低位
		byte byte3 = a.byteValue();

		// 用javap查看字节码 Short会使用invokestatic指令

	}
}
