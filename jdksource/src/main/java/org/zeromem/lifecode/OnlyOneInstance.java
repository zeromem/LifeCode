package org.zeromem.lifecode;

public class OnlyOneInstance {
    private static OnlyOneInstance ourInstance = new OnlyOneInstance();

    public static OnlyOneInstance getInstance() {
        return ourInstance;
    }

    private OnlyOneInstance() {
    }

    public static void main(String[] args) {
    }

}
