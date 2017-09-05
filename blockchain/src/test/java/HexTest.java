import org.bouncycastle.util.encoders.Hex;

import java.util.Arrays;

/**
 * Created by zeromem on 2017/8/10.
 */
public class HexTest {
	public static void main(String[] args) {
		System.out.println(hexToAscii("0x7cd22a280bed478c11d7043708bfc0771f30dadc651aeb684359e09091cd9ea5"));
	}

	private static String hexToAscii(String hexStr) {
		StringBuilder output = new StringBuilder("");

		for (int i = 2; i < hexStr.length(); i += 2) {
			String str = hexStr.substring(i, i + 2);
			output.append((char) Integer.parseInt(str, 16));
		}

		return output.toString();
	}
}
