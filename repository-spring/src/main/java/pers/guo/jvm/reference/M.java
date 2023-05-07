package pers.guo.jvm.reference;

/**
 * @author abner
 * @version 1.0
 * @description: TODO
 * @date 2023/5/7 16:25
 */
public class M {

    @Override
    protected void finalize() throws Throwable{
        System.out.println("执行finalize 方法，在对象回收之前会调用此方法");
    }

}
