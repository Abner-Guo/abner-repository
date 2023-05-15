package pers.guo.jvm.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: guochao.bj@fang.com
 * @createDate: 2023/5/15 17:27
 */
public class ExecutorsDemo {

    public static void main(String[] args) {
        /**
         * 核心线程数据固定的线程池
         *
         * 队列使用LinkedBlockingQueue
         * 默认构造函数，可以看作无界的队列
         * 容易造成OOM
         */

        ExecutorService executorService1 = Executors.newFixedThreadPool(1);

        /**
         * 核心线程数据固定的线程池
         *
         * 队列使用LinkedBlockingQueue
         * 默认构造函数，可以看作无界的队列
         * 容易造成OOM
         */
        ExecutorService executorService3 = Executors.newSingleThreadExecutor();

        /**
         * 返回核心线程数据为0，最大线程为int最大值
         *
         * 不设置线程限制，有任务就创建线程，这样也可能造成OOM
         */
        ExecutorService executorService = Executors.newCachedThreadPool();

        /**
         * 返回最大线程为int最大值
         *
         * 不设置线程限制，有任务就创建线程，这样也可能造成OOM
         */
        ExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

    }
}
