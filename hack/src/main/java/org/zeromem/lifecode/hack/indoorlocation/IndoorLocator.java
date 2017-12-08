package org.zeromem.lifecode.hack.indoorlocation;

import com.alibaba.fastjson.JSON;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

import static org.zeromem.lifecode.hack.indoorlocation.RssiConvertor.*;


/**
 * Created by zeromem on 2017/4/25.
 * 室内定位器.
 * 同一个店铺的所有定位需求均可以复用同一个locator (具有相同的oneMeterRssi和metersPerPixel参数).
 *
 */
public class IndoorLocator {
	private static final Logger log = LoggerFactory.getLogger(IndoorLocator.class);
	private static final Random random = new Random();

	/**
	 * 距离wifi 1m 时的rssi值，经验值50-60之间.用于将rssi转换为距离。
	 */
//	private final int oneMeterRssi;


	/**
	 * 即scale, 电子平面图中1 pixel表示多少米. 作为scale将米制距离转换为像素数量
	 */
//	private final double metersPerPixel;


//	public IndoorLocator(int oneMeterRssi, double metersPerPixel) {
//		this.oneMeterRssi = oneMeterRssi;
//		this.metersPerPixel = metersPerPixel;
//	}


	public static Optional<double[]> predictByWifiData(ArrayList<LocatePixInfo> locatePixInfos) {
		int size = locatePixInfos.size();
		switch (size) {
			case 0: {
				log.error("Cannot predict location with no wifi data! Please add wifi data first!");
				return Optional.empty();
			}
			case 1: {
				return Optional.of(predictByOneWifi(locatePixInfos.get(0)));
			}
			case 2: {
				return Optional.of(predictByTwoWifi(locatePixInfos.get(0), locatePixInfos.get(1)));
			}
			default: {
				SimpleRegression regression = new SimpleRegression(true);
				double[][] regressionInput = calcRegressionDataByDistances(locatePixInfos);
				regression.addData(regressionInput);
				return Optional.of(new double[]{regression.getIntercept(), regression.getSlope()});
			}
		}
	}


	/**
	 * 工具方法，供单wifi和双wifi预测使用
	 * @param locatePixInfo
	 * @param theta
	 * @return
	 */
	private static double[] predictByOneWifi(LocatePixInfo locatePixInfo, double theta) {
		double x = locatePixInfo.pixX + locatePixInfo.pixDistance * Math.cos(theta);
		double y = locatePixInfo.pixY + locatePixInfo.pixDistance * Math.sin(theta);
		return new double[]{x, y};
	}


	/**
	 * 只有一个wifi定位数据
	 * @param locatePixInfo
	 * @return
	 */
	private static double[] predictByOneWifi(LocatePixInfo locatePixInfo) {
		double theta = random.nextDouble() * Math.PI;
		return predictByOneWifi(locatePixInfo, theta);
	}


	/**
	 *
	 * @param locatePixInfo1
	 * @param locatePixInfo2
	 * @return
	 */
	private static double[] predictByTwoWifi(LocatePixInfo locatePixInfo1, LocatePixInfo locatePixInfo2) {
		// 转换成单wifi预测
		double x1 = locatePixInfo1.pixX, x2 = locatePixInfo2.pixX;
		double y1 = locatePixInfo1.pixY, y2 = locatePixInfo2.pixY;
		double d1 = locatePixInfo1.pixDistance, d2 = locatePixInfo2.pixDistance;
		double theta = Math.atan((y2 - y1) / (x2 - x1));
		double D = Math.pow((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1), 0.5);
		double d = (D + d1 - d2) / 2;
		return predictByOneWifi(new LocatePixInfo(x1, y1, d), theta);
	}


	/////////////// utils ///////////////////////
	private static double[][] calcRegressionDataByDistances(ArrayList<LocatePixInfo> locatePixInfos) {
		double[][] data = new double[locatePixInfos.size() - 1][2];
		double x0 = locatePixInfos.get(0).pixX;
		double y0 = locatePixInfos.get(0).pixY;
		double d0 = locatePixInfos.get(0).pixDistance;

		for (int i = 0; i < data.length; i++) {
			double xi = locatePixInfos.get(i + 1).pixX;
			double yi = locatePixInfos.get(i + 1).pixY;
			double di = locatePixInfos.get(i + 1).pixDistance;
			data[i][0] = (y0 - yi) / (x0 - xi);
			data[i][1] = ((di + d0) * (di - d0) + (y0 - yi) * (y0 + yi)) / (2 * (x0 - xi)) + (x0 + xi) / 2;
		}
		return data;
	}


	public static void main(String[] args) {
		///////////// 准备输入
		ArrayList<LocatePixInfo> locatePixInfos = new ArrayList<>();
		double[][] wifiLocations = {{300, 150}, {1200, 300}, {1000, 800}};
		double[] distances = {7.8, 2.5, 3};

		//// 计算rssiList模拟定位输入
		int[] rssiList = new int[3];
		for (int i = 0; i < 3; i++) {
			rssiList[i] = calcRssiByDistance(distances[i], DEFAULT_RSSI_ONE_METER, DEFAULT_SIGNAL_ATTENUATION);

		}
		log.info("rssi list: " + JSON.toJSONString(rssiList));


		int someMeters = 20;
		double somePixels = 2000;
		double scale = someMeters / somePixels;

		//////// client端负责生成List[LocatePixInfo]
		locatePixInfos.add(new LocatePixInfo(wifiLocations[0][0], wifiLocations[0][1], calcPixelsByRssi(rssiList[0], scale)));
		locatePixInfos.add(new LocatePixInfo(wifiLocations[1][0], wifiLocations[1][1], calcPixelsByRssi(rssiList[1], scale)));
//		locatePixInfos.add(new LocatePixInfo(wifiLocations[2][0], wifiLocations[2][1], calcPixelsByRssi(rssiList[2], scale)));

		Optional<double[]> optional = IndoorLocator.predictByWifiData(locatePixInfos);
		if (optional.isPresent()) {
			log.info("result: " + JSON.toJSONString(optional.get()));
		} else {
			log.error("No result!");
		}
	}
}

