package pers.guo.jvm.classload;

/**
 * @author abner
 * @version 1.0
 * @description: 类加载联系
 * @date 2023/5/11 22:39
 */
public class ClassLoadingProcedure {

    public static void main(String[] args) {
        System.out.println(T.count); //3
    }

}


class T{

    public static int count=2;
    public static T t=new T();

    private T (){
        count++;
    }

}