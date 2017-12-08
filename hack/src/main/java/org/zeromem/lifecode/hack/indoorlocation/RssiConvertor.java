package org.zeromem.lifecode.hack.indoorlocation;

/**
 * Created by zeromem on 2017/4/27.
 * rssi转米制距离及米制距离转像素长度.
 * flowinsight 用的rssi是正数，一般公式给的是负数。注意flowinsight应该用如下公式
 * RSSI = A + 10n*lgd
 * pixDistance = 10 ^ ((RSSI - A) / 10n)
 * A: rssi value at pixDistance is 1 meter. [50, 60]
 * n: signal attenuation factor. [2, 4]
 */
public final class RssiConvertor {
	public static final int DEFAULT_RSSI_ONE_METER = 55;
	public static final double DEFAULT_SIGNAL_ATTENUATION = 1.6;


	//////////// rssi -> distance. //////////////////////
	public static double calcDistanceByRssi(int rssi, int A, double n) {
		return Math.pow(10, (rssi - A) / (10 * n));
	}

	public static double calcDistanceByRssi(int rssi) {
		return calcDistanceByRssi(rssi, DEFAULT_RSSI_ONE_METER, DEFAULT_SIGNAL_ATTENUATION);
	}

	public static double calcDistanceByRssi(int rssi, int A) {
		return calcDistanceByRssi(rssi, A, DEFAULT_SIGNAL_ATTENUATION);
	}


	//////////// distance -> rssi. //////////////////////
	public static int calcRssiByDistance(double distance, int A, double n) {
		return (int) (A + 10 * n * Math.log10(distance));
	}


	///////////// distance to pixels. /////////////////////
	/**
	 * 米制距离转像素距离. 需client给定比例 1 pixel : pixX meters.
	 * @param distance
	 * @param scale 比例 `scale` meters per pixel. meter / pixel
	 *
 	 */
	public static double calcPixelsByDistance(double distance, double scale) {
		return distance / scale;
	}

	///////////// pixels to distance. /////////////////////
	public static double calcDistanceByPixels(double pixels, double scale) {
		return pixels * scale;
	}


	///////////// rssi to pixels. /////////////////////
	public static double calcPixelsByRssi(int rssi, int A, double scale) {
		double distanceMeters = calcDistanceByRssi(rssi, A);
		return calcPixelsByDistance(distanceMeters, scale);
	}

	public static double calcPixelsByRssi(int rssi, double scale) {
		return calcPixelsByRssi(rssi, DEFAULT_RSSI_ONE_METER, scale);
	}


	///////////// pixels to rssi. /////////////////////
	public static int calcRssiByPixels(double pixels, int A, double scale) {
		double distance = calcDistanceByPixels(pixels, scale);
		return calcRssiByDistance(distance, A, DEFAULT_SIGNAL_ATTENUATION);
	}


	public static void main(String[] args) {
//		System.out.println(calcDistanceByRssi(70));
//		System.out.println(calcRssiByDistance(3, DEFAULT_RSSI_ONE_METER, DEFAULT_SIGNAL_ATTENUATION));
//		System.out.println(calcPixelsByRssi(70, 15 / 2000D));

		double res = 1 / 3D;
		System.out.println(res);
	}
}
