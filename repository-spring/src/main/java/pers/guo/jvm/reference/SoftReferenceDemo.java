package pers.guo.jvm.reference;

import java.lang.ref.SoftReference;

/**
 * @author abner
 * @version 1.0
 * @description: 软引用，非常适用于缓存
 * @date 2023/5/7 16:37
 */
public class SoftReferenceDemo {

    /**
     * 启动设置 -Xmx9M
     * @param args
     */
    public static void main(String[] args) {

       SoftReference<byte[]> softReference= new SoftReference<>(new byte[1024*1024*5]);

        System.out.println(softReference.get());//[B@72ea2f77

        System.gc();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(softReference.get());//[B@72ea2f77

        //这里重新创建一个数组，内存不够，会先进行一次垃圾回收，如果不够会垃圾回收掉软引用的对象
        byte[] bytes = new byte[1024 * 1024 * 6];
        System.out.println(softReference.get());//null
    }

}
