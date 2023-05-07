package pers.guo.jvm.reference;

import java.lang.ref.WeakReference;

/**
 * @author abner
 * @version 1.0
 * @description: 弱引用 ThreadLocal中解决内存泄漏问题
 * @date 2023/5/7 17:33
 */
public class WeakReferenceDemo {

    public static void main(String[] args) {
        WeakReference<M> mWeakReference = new WeakReference<>(new M());

        System.out.println(mWeakReference.get());

        System.gc();

        System.out.println(mWeakReference.get());
    }

}
