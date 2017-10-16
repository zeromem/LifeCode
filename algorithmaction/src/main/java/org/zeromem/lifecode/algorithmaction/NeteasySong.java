package org.zeromem.lifecode.algorithmaction;

/**
 * Created by zeromem on 2017/10/16.
 小Q和牛博士合唱一首歌曲,这首歌曲由n个音调组成,每个音调由一个正整数表示。
 对于每个音调要么由小Q演唱要么由牛博士演唱,对于一系列音调演唱的难度等于所有相邻音调变化幅度之和, 例如一个音调序列是8, 8, 13, 12, 那么它的难度等于|8 - 8| + |13 - 8| + |12 - 13| = 6(其中||表示绝对值)。
 现在要对把这n个音调分配给小Q或牛博士,让他们演唱的难度之和最小,请你算算最小的难度和是多少。
 如样例所示: 小Q选择演唱{5, 6}难度为1, 牛博士选择演唱{1, 2, 1}难度为2,难度之和为3,这一个是最小难度和的方案了。

 Input
 输入包括两行,第一行一个正整数n(1 ≤ n ≤ 2000) 第二行n个整数vi, 表示每个音调。

 Output
 输出一个整数,表示小Q和牛博士演唱最小的难度和是多少。

 Sample Input
 5
 1 5 6 2 1
 Sample Output
 3
 */
public class NeteasySong {
	public static void main(String[] args) {

	}
}
