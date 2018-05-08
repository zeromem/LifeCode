package org.zeromem.lifecode.hack;

import java.nio.ByteBuffer;
import sun.nio.ch.DirectBuffer;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zeromem
 * @date 2018/4/16
 * off heap的大小默认和-Xmx一样大，
 * 可以使用XX:MaxDirectMemorySize来单独设置
 */
public class OffHeapMem {
    public static void main(String[] args) {
        List<ByteBuffer> buffers = new LinkedList<>();
        // 如果在vm option中禁用显式gc(-XX:+DisableExplicitGC),则会OOM: direct buffer memory.
        int i = 0;
        while (i < 1000) {
            ByteBuffer buffer = ByteBuffer.allocateDirect(10 * 1024 * 1024);
            // buffers.add(buffer);
            i++;
        }
        // ((DirectBuffer) buffers.get(0)).cleaner().clean();
    }
}
