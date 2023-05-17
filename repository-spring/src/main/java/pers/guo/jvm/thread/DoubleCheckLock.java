package pers.guo.jvm.thread;

/**
 * @author: guochao.bj@fang.com
 * @createDate: 2023/5/17 18:01
 * DCL 双重检查单例模式
 */
public class DoubleCheckLock {

    private static volatile   DoubleCheckLock DOUBLECHECKLOCK;



    private DoubleCheckLock(){};

    public static DoubleCheckLock getDoubleCheckLock(){

        if (DOUBLECHECKLOCK==null){
            //如果不加volatile修饰，发生指令重排，这里判断就会是！=null的情况,方法return 一个不为 null 但是未完成初始化的对象

            synchronized (DoubleCheckLock.class){
                if (DOUBLECHECKLOCK==null){
                    //如果DOUBLECHECKLOCK 不用 volatile修饰，new对象过程可能发生指令重排问题
                    DOUBLECHECKLOCK=new DoubleCheckLock();
                }
            }
        }
        return DOUBLECHECKLOCK;
    }

    public  void  sout(){
        System.out.println("DCL-------------");
    }


    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    DoubleCheckLock.getDoubleCheckLock().sout();
                }
            }).start();
        }

    }

}
