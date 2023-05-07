package pers.guo.jvm.thread;

import pers.guo.jvm.reference.M;

/**
 * @author abner
 * @version 1.0
 * @description: ThreadLocal 内存泄漏问题
 * @date 2023/5/7 23:35
 */
public class ThreadLocalDemo {

    public static void main(String[] args) {
        ThreadLocal<M> mThreadLocal = new ThreadLocal<>();

        /**
         * ThreadLocal 内部使用弱引用来解决内存泄漏问题
         * set() 底层是往Thread 对象属性ThreadLocalMap中存相关信息。
         * key 为 ThreadLocal对象本身，value 为信息
         * 会将key,value组装成entry对象，并且key会使用弱引用指向 ThreadLocal对象本身。
         *
         * 如果这里变为强引用，在ThreadLocal对象本身不在使用的情况下，因为ThreadLocalMap中key强引用的存在
         * ThreadLocal对象本身 永远不会被回收
         */
        mThreadLocal.set(new M());

        /**
         * set方法内部key使用弱引用解决内存泄漏问题
         * 在GC之后，key会变为null,这样ThreadLocalMap会存在<null,value> 数据，value永远回收不了
         * 也会造成内存泄漏问题
         *
         * 所以在使用完ThreadLocal对象之后，要remove掉，防止内存泄漏
         */
        mThreadLocal.remove();

    }


}
