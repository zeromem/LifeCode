package org.zeromem.lifecode.hack.concurrent.tas;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author zeromem
 * @date 2018/2/24
 * TTAS比TAS更高效
 * 在多处理器结构中，getAndSet方法会迫使处理器p向总线广播请求，
 * 且强迫其他相关处理器更新cache中的state值。
 * 使用TTAS，可以减少getAndSet的调用。
 */
public class TestTestAndSetLock {
    AtomicBoolean state = new AtomicBoolean(false);

    public void lock() {
        while (true) {
            while (state.get()) {};
            if (!state.getAndSet(true)) {
                return;
            }
        }
    }

    public void unlock() {
        state.set(false);
    }
}
