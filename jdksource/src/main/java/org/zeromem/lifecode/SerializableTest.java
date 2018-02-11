package org.zeromem.lifecode;

import java.io.*;

/**
 * @author zeromem
 * @date 2018/2/9
 */
public class SerializableTest {
    public static void main(String[] args) {
        SerObject o = new SerObject("zeromem", 25);

        try (
                FileOutputStream fos = new FileOutputStream("D:\\serobject");
                ObjectOutputStream oos = new ObjectOutputStream(fos)
        ) {
            // 在ObjectOutputStream内部的writeObject0方法中，有条件判断
            // 参数必须是String 或 Enum 或 Serializable，否则将抛出NotSerializableException
            oos.writeObject(o);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (
                FileInputStream fis = new FileInputStream("D:\\serobject");
                ObjectInputStream ois = new ObjectInputStream(fis)
        ) {
            SerObject so = (SerObject) ois.readObject();
            System.out.println(so);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

class SerObject {
    String name;
    int age;

    public SerObject(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "SerObject{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
