package pers.guo.jvm.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.List;

/**
 * @author abner
 * @version 1.0
 * @description: 虚引用，处理堆外内存
 * @date 2023/5/7 17:41
 */
public class PhantomReferenceDemo {

    private static final List<Object> LIST =new ArrayList<>();
    private static final ReferenceQueue QUEUE=new ReferenceQueue();


    public static void main(String[] args) {
        final PhantomReference<M> mPhantomReference = new PhantomReference<M>(new M(), QUEUE);

        new Thread(()->{
            while (true){
                //死循环往内存存放数组，迫使发生gc,回收虚引用对象
                LIST.add(new byte[1024*1024*10]);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //虚引用永远拿不到具体的值。但是被回收之后，会在ReferenceQueue中添加一个被回收的信息到队列中
                System.out.println(mPhantomReference.get());
            }
        }).start();

        new Thread(()->{
            while (true){
                Reference<? extends M> poll = QUEUE.poll();
                if (poll!=null){
                    System.out.println("虚引用对象被♻️"+poll);
                }
            }
        }).start();
    }
}
