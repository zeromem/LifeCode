package org.zeromem.lifecode.algorithmaction.leetcode;

/**
 * Created by zuochuang on 2017/4/4.
 * Now you are given a string S, which represents a software license key which we would like to format. The string S is composed of alphanumerical characters and dashes. The dashes split the alphanumerical characters within the string into groups. (i.e. if there are M dashes, the string is split into M+1 groups). The dashes in the given string are possibly misplaced.

 We want each group of characters to be of length K (except for possibly the first group, which could be shorter, but still must contain at least one character). To satisfy this requirement, we will reinsert dashes. Additionally, all the lower case letters in the string must be converted to upper case.

 So, you are given a non-empty string S, representing a license key to format, and an integer K. And you need to return the license key formatted according to the description above.

 Example 1:
 Input: S = "2-4A0r7-4k", K = 4

 Output: "24A0-R74K"

 Explanation: The string S has been split into two parts, each part has 4 characters.
 Example 2:
 Input: S = "2-4A0r7-4k", K = 3

 Output: "24-A0R-74K"

 Explanation: The string S has been split into three parts, each part has 3 characters except the first part as it could be shorter as said above.
 Note:
 The length of string S will not exceed 12,000, and K is a positive integer.
 String S consists only of alphanumerical characters (a-z and/or A-Z and/or 0-9) and dashes(-).
 String S is non-empty.
 */
public class _482Key {
	public static void main(String[] args) {
		char[] value = "hello world".toCharArray();
		System.out.println(reverse(value, value.length));

		char a = 'a';
		char A = 'A';
		System.out.println(a - A);
		_482Key test = new _482Key();
		System.out.println(test.licenseKeyFormatting2("2-4A0r7-4k", 3));
		System.out.println(test.licenseKeyFormatting2("---", 3));
	}


	public String licenseKeyFormatting(String S, int K) {
		StringBuilder sb = new StringBuilder(S.length());
		int tmp = 0;
		for (int i = S.length() - 1; i >= 0; i--) {
			char c = S.charAt(i);
			if (c == '-') {
				continue;
			}

			sb.append(c >= 97 ? (char)(c - 32) : c);
			if (++tmp == K) {
				sb.append('-');
				tmp = 0;
			}
		}

		int lastIndex = sb.length() - 1;
		if (lastIndex >= 0 && sb.charAt(lastIndex) == '-') {
			sb.deleteCharAt(sb.length() - 1);
		}
		return new String(sb.reverse());
	}


	public String licenseKeyFormatting2(String S, int K) {
		char[] value = new char[S.length() + S.length() / K];
		int tmp = 0, pos = 0;
		for (int i = S.length() - 1; i >= 0; i--) {
			char c = S.charAt(i);
			if (c == '-') {
				continue;
			}

			value[pos++] = (c >= 97 ? (char)(c - 32) : c);
			if (++tmp == K) {
				value[pos++] = '-';
				tmp = 0;
			}
		}

		if (pos - 1 >= 0 && value[pos - 1] == '-') {
			pos--;
		}
		return new String(reverse(value, pos), 0, pos);
	}


	public static char[] reverse(char[] value, int n) {
		n--;
		for (int i = (n - 1) >> 1; i >= 0; i--) {
			int k = n - i;
			char ci = value[i];
			value[i] = value[k];
			value[k] = ci;
		}
		return value;
	}
}
