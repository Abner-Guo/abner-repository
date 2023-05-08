package pers.guo.jvm.thread;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * @author: guochao.bj@fang.com
 * @createDate: 2023/5/8 10:32
 * @description: ThreadLocal 本地变量传递问题
 */
public class TtlItlThreadLocalDemo {

    public static volatile Integer i=1;

//    private static final ThreadLocal<Map<String, String>> TL = new ThreadLocal<>();

//    private static final ThreadLocal<Map<String, String>> TL = new InheritableThreadLocal<>();

   private static final ThreadLocal<Map<String, String>> TL = new TransmittableThreadLocal<>();



    private static Map<String,String> getMap(ThreadLocal<Map<String, String>> threadLocal){
        if (threadLocal.get()!=null){
            Map<String, String> stringStringHashMap = new HashMap<>();
            stringStringHashMap.putAll(threadLocal.get());
            return stringStringHashMap;
        }else {
            return new HashMap<String, String>();
        }
    }

    /**
     * 使用线程池方法
     * @author guochao.bj@fang.com
     * @date 2023/5/8
     */
    private static Executor getExecutor(){
        Executor executor=null;
        ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();
        threadPool.setCorePoolSize(1);
        threadPool.initialize();
        executor=threadPool;

        if (TL.getClass().getSimpleName().equals("TransmittableThreadLocal")){
            executor= TtlExecutors.getTtlExecutor(threadPool);
        }
        CompletableFuture.runAsync(()->{
            Map<String, String> saMap = new HashMap<>();
            saMap.put(Thread.currentThread().getName()+i,"0");
            TL.set(saMap);
            i++;
        },executor);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return executor;
    }


    public static void main(String[] args) {
        //使用线程池
        Executor executor = getExecutor();



        Map<String, String> sashMap = getMap(TL);
        sashMap.put(Thread.currentThread().getName(),"1");

        TL.set(sashMap);
        System.out.println(Thread.currentThread().getName()+"【"+TL.getClass().getSimpleName()+"】"+TL.get());


        CompletableFuture.runAsync(()->{
            Map<String, String> TLmap = getMap(TL);

            TLmap.put(Thread.currentThread().getName()+i,"2");
            TL.set(TLmap);
            System.out.println(Thread.currentThread().getName()+"【"+TL.getClass().getName()+"】"+TL.get());
        },executor);



//        Runnable r = () -> {
//            Map<String, String> TLmap = getMap(TL);
//            TLmap.put(Thread.currentThread().getName(),"2");
//            TL.set(TLmap);
//            System.out.println(Thread.currentThread().getName()+"【"+TL.getClass().getSimpleName()+"】"+TL.get());
//        };
//        Thread t = new Thread(r);
//        t.start();




        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"【"+TL.getClass().getSimpleName()+"】"+TL.get());

    }



}
