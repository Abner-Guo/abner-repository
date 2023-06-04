package pers.guo.design.singleton;

/**
 * @author abner
 * @version 1.0
 * @description: 单例模式，饿汉
 * 存在弊端：不管是否使用，在类加载过程都会发生实例化
 * 简单推荐使用
 * @date 2023/6/3 09:43
 */
public class SingletonDemo01 {


    private static SingletonDemo01 demo=new SingletonDemo01();

    private SingletonDemo01(){};

    public SingletonDemo01 getDemo(){
        demo=new SingletonDemo01();
        return demo;
    }


}
