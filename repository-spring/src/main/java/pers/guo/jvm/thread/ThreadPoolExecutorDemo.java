package pers.guo.jvm.thread;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池源码Demo
 *
 * @author: guochao.bj@fang.com
 * @createDate: 2023/5/16 09:17
 */
public class ThreadPoolExecutorDemo {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 50, 30, TimeUnit.MILLISECONDS, new SynchronousQueue<Runnable>());
        executor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("ThreadPoolExecutorDemo-------------------------");
            }
        });

        executor.shutdown();
        executor.shutdownNow();
    }




}
