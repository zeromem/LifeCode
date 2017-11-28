package org.zeromem.lifecode.algorithmaction.leetcode;

/**
 * Created by zeromem on 2017/4/26.
 * 01矩阵中最大全1子矩阵
 */
public class _85MaximalRect {
	public static void main(String[] args) {
		_85MaximalRect test = new _85MaximalRect();
		int[][] M = {{1, 0, 1, 0, 0}, {1, 0, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 0, 0, 1, 0}};
		System.out.println(test.maxRectInRect(M));
	}


	public int maxRectInRect(int[][] M) {
		int[][] hists = histogram(M);
		int max = -1;
		for (int i = 0; i < hists.length; i++) {
			int rect = maxRectInHist(hists[i]);
			if (rect > max) {
				max = rect;
			}
		}

		return max;
	}


	public int[][] histogram(int[][] M) {
		int[][] hist = new int[M.length][M[0].length];
		System.arraycopy(M[0], 0, hist[0], 0, M[0].length);

		for (int i = 1; i < M.length; i++) {
			for (int j = 0; j < M[0].length; j++) {
				hist[i][j] = M[i][j] == 1 ? hist[i - 1][j] + 1 : 0;
			}
		}
		return hist;
	}

	public int maxRectInHist(int[] hist) {
		int[] left = new int[hist.length];
		int[] right = new int[hist.length];

		for (int i = 0; i < hist.length; i++) {
			int pos = i;
			while (pos >= 0 && hist[pos] >= hist[i]) {
				pos--;
			}
			left[i] = pos + 1;
			pos = i;
			while (pos < hist.length && hist[pos] >= hist[i]) {
				pos++;
			}
			right[i] = pos - 1;
		}

		int max = -1;
		for (int i = 0; i < hist.length; i++) {
			int rect = (right[i] - left[i] + 1) * hist[i];
			if (rect > max) {
				max = rect;
			}
		}

		return max;
	}
}
