package pers.guo.jvm.gc;

import java.util.LinkedList;

/**
 * @author: guochao.bj@fang.com
 * @createDate: 2023/5/22 14:22
 */
public class HelloGC {


    /**
     * 配置参数
     * -Xmn3M -Xms9M -Xmx9M -XX:+PrintCommandLineFlags -XX:+PrintGCDetails -XX:+PrintGC
     *
     * G1垃圾回收器测试参数
     * -Xms9M -Xmx9M -XX:+PrintGCDetails -XX:+UseG1GC
     *
     * @author guochao.bj@fang.com
     * @date 2023/5/22
     */
    public static void main(String[] args) {
        LinkedList<byte[]> bytes = new LinkedList<>();

        for (;;){
            byte[] bytes1 = new byte[1024 * 1024];
            bytes.add(bytes1);
        }
    }

}
