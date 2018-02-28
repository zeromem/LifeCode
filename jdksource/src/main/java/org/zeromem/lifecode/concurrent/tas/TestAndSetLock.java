package org.zeromem.lifecode.concurrent.tas;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author zeromem
 * @date 2018/2/24
 */
public class TestAndSetLock {
    AtomicBoolean state = new AtomicBoolean(false);

    public void lock() {
        while (state.getAndSet(true)) {

        }
    }

    public void unlock() {
        state.set(false);
    }
}
