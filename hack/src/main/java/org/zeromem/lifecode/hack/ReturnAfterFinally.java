package org.zeromem.lifecode.hack;

/**
 * @author zeromem
 * @date 2017/11/9
 */
public class ReturnAfterFinally {
    public static void main(String[] args) {
        ReturnAfterFinally test = new ReturnAfterFinally();
        System.out.println(test.surprise(true));
    }

    public boolean surprise(boolean in) {
        // 方法将始终返回false
        while (in) {
            try {
                System.out.println("TRY");
                return true;
            } finally {
                System.out.println("FIN");
                break;
            }
        }
        System.out.println("AFTER");
        return false;
    }
}
