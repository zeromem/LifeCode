package org.zeromem.lifecode.hack.concurrent;

import java.util.concurrent.*;

/**
 * @author zeromem
 * @date 2018/2/22
 */
public class ComplexDemo {
    public static void main(String[] args) {
        // 0. create task thread queue
        BlockingQueue<Runnable> queue = new LinkedBlockingDeque<>();

        // 1. create thread pool (ExecutorService)
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                10, 50,
                10, TimeUnit.SECONDS,
                queue);

        // 2. create CompletionService
        CompletionService<String> service = new ExecutorCompletionService<>(executor);

        // create task
        Callable<String> task = () -> "hello world";

        // 3. submit task(callable task) to service, not to executor.
        service.submit(task); // RejectionExecutionException

        // ...

        // 4. take result from service
        int TASK_SIZE = 100;
        for (int i = 0; i < TASK_SIZE; i++) {
            try {
                String result = service.take().get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                // task future may failed.
                e.printStackTrace();
            }
        }

    }
}
