package org.zeromem.lifecode.hack;

import java.io.*;

/**
 * Created by zeromem on 2016/11/13.
 * 读写文件测试
 * 读文件用new BufferedReader(
 *              new InputStreamReader(
 *                  new FileInputStream(new File(path)),
 *                  "CHAR_SET"
 *              )
 *           );
 * InputStreamReader第二个参数指定编码方式
 *
 * 对应的写文件用 new BufferedWriter(
 *                  new OutputStreamWriter(
 *                      mew FileOutputStream(
 *                          new File(path),
 *                          true // open file in append mode.
 *                      ),
 *                      "CHAR_SET"
 *                  )
 *             );
 * FileOutputStream第二个参数指定追加模式，OutputStreamWriter第二个参数指定编码方式
 */
public class StringTest {
    public static void main(String[] args) throws IOException {
//        System.out.println(Charset.defaultCharset());
        String a = "你好";
        byte[] acs = a.getBytes("Unicode");

        for (int i = 0; i < acs.length; i++) {
            System.out.println(acs[i]);
        }

        // 读文件，用new BufferedReader(new InputStreamReader(new FileInputStream(new File()), "CHAR_SET"))
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("./test")), "GBK"));
        String line = br.readLine();
        System.out.println(line);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("./testout"), true), "UTF-8"));
        bw.append("中文GBK写中文测试");
        bw.flush();
        bw.close();
        br.close();
    }
}
