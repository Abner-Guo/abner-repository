package pers.guo.design.singleton;

/**
 * @author abner
 * @version 1.0
 * @description: 饿汉模式实现懒加载，类加载过程不会加载内部类,只有在使用到的时候才会实例话SingletonDemoHolder
 * @date 2023/6/3 09:48
 */
public class SingletonDemo02 {

    private SingletonDemo02(){};


    private static class SingletonDemoHolder{
        private final static SingletonDemo02 demo=new SingletonDemo02();
    }

    public static SingletonDemo02 getDemo(){
        return SingletonDemoHolder.demo;
    }


}
