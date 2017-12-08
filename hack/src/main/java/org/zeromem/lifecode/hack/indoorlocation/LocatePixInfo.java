package org.zeromem.lifecode.hack.indoorlocation;

/**
 * Created by zeromem on 2017/4/27.
 * <p>
 *     描述wifi对一个设备的定位信息:wifi位置及预估距离，均以像素为单位。
 *     pixX为wifi相对平面图原点的横坐标像素值, pixY为wifi相对平面图原点的纵坐标像素值.
 *     pixDistance为预估的设备到wifi的像素距离
 * </p>
 *
 */
class LocatePixInfo {
	public final double pixX;
	public final double pixY;
	public final double pixDistance;

	public LocatePixInfo(double pixX, double pixY, double pixDistance) {
		this.pixX = pixX;
		this.pixY = pixY;
		this.pixDistance = pixDistance;
	}
}
