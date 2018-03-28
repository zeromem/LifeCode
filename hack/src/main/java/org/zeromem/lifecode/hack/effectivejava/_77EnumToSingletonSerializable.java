package org.zeromem.lifecode.hack.effectivejava;

import java.io.*;
import java.util.Arrays;

/**
 * @author zeromem
 * @date 2018/2/11
 * 如果类是一个单例且需要序列化，则应该使用单元素的枚举
 */
public class _77EnumToSingletonSerializable {
    public _77EnumToSingletonSerializable() {
    }

    public static void main(String[] args) {
        Only one = Only.INSTANCE;
        System.out.println(one);
        one.print();

        try (
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:\\testo"));
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream("D:\\testo"))
        ) {
           oos.writeObject(one);
            Only o = (Only) ois.readObject();
            System.out.println(o);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}

enum Only {
    INSTANCE;

    Only() {

    }

    private String[] dict = {"hello", "world"};

    public void print() {
        System.out.println(Arrays.toString(dict));
    }
}
