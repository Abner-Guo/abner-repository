package pers.guo.jvm.reference;

/**
 * @author abner
 * @version 1.0
 * @description: 强引用demo
 * @date 2023/5/7 16:28
 */
public class NormalReference {

    public static void main(String[] args) {
        M m = new M();
        //没有对象引用会进行回收
        m=null;

        System.gc();
    }

}
