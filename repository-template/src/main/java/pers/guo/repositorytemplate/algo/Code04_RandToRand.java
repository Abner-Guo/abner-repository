package pers.guo.repositorytemplate.algo;

/**
 * 对数器
 * @author abner
 * @version 1.0
 * @description: 对数器测试
 * @date 2023/8/21 22:33
 */
public class Code04_RandToRand {

    /**
     * 随机函数Math.random
     *
     * 对数器
     */

    public static void main(String[] args) {
        System.out.println("test start ...");

        int testTime = 1000000;
        int count=0;
        for (int i = 0; i < testTime; i++) {
            if (Math.random()<0.75){
                count++;
            }
        }
        System.out.println("随机函数Math.random [0,1) 中[0,0.75) 的概率"+(double)count/(double) testTime);
        System.out.println("---------------------------------------------");
        count=0;
        for (int i = 0; i < testTime; i++) {
            if (Math.random()*8 <5){
                count++;
            }
        }

        System.out.println("[0,1)->[0,8)中[0,5)的概率"+(double)count/(double) testTime);
        System.out.println((double)5/(double)8);
        System.out.println("---------------------------------------------");
        int K = 9;
        // [0,K) -> [0,8]

        int[] counts = new int[9];
        for (int i = 0; i < testTime; i++) {
            int ans = (int) (Math.random() * K); // [0,K-1]
            counts[ans]++;
        }
        for (int i = 0; i < K; i++) {
            System.out.println(i + "这个数，出现了 " + counts[i] + " 次");
        }
        System.out.println("---------------------------------------------");

        count = 0;
        double x = 0.17;
        for (int i = 0; i < testTime; i++) {
            if (xToXPower2() < x) {
                count++;
            }
        }
        System.out.println("实现x^2概率 ->"+(double) count / (double) testTime);
        System.out.println((Math.pow(x, 2)));
        System.out.println("---------------------------------------------");

        counts = new int[8];
        for (int i = 0; i < testTime; i++) {
            int num = g();
            counts[num]++;
        }
        for (int i = 0; i < 8; i++) {
            System.out.println(i + "这个数，出现了 " + counts[i] + " 次");
        }





    }

    /***
     * @description:  Math.random()->[0,x) 在[0,1)中出现的概率为x。实现x^2概率
     * @param:
     * @return: double
     * @author abner
     * @date: 2023/8/21 23:00
     */
    public static double xToXPower2(){

        /**
         * Math.max：[0,x) 出现的概率为：两个Math.random()取最大值；如果要取[0,x)，两个的概率必须为x,x*x=x^2
         * Math.min:[0,x) 出现的概率为：取两个Math.random()取最小值；如果要取[0,x),只要有一个概率为x就可以,所以两个在一起的概率没办法计算
         *                              换一种思路，不取[0,x),那么两个的概率必须都是（1-x），这样概率就是1-(1-x)^2
         *
         */

        return Math.max(Math.random(),Math.random());
    }

    /***
     * @description:  默认提供函数[1~6]出现的概率相等
     * @param:
     * @return: int
     * @author abner
     * @date: 2023/8/21 23:21
     */
    public static int f1(){
        return (int)(Math.random()*5)+1;
    }

    /***
     * @description:  将[1~6]等概率转换为[0~1] 等概率
     * @param:
     * @return: int
     * @author abner
     * @date: 2023/8/21 23:24
     */
    public static int f2(){
        int ans=0;
        do {
            ans=f1();
        }while (ans==3);
        return ans<3?0:1;
    }

    /***
     * @description: 使用二进制 000 ~ 111 做到等概率 0 ~ 7等概率返回一个
     * @param:
     * @return: int
     * @author abner
     * @date: 2023/8/21 23:28
     */
    public static int f3() {
        return (f2() << 2) + (f2() << 1) + f2();
    }

    /***
     * @description:  0~7 等概率转化为0～6等概率
     * @param:
     * @return: int
     * @author abner
     * @date: 2023/8/21 23:30
     */
    public static int f4() {
        int ans = 0;
        do {
            ans = f3();
        } while (ans == 7);
        return ans;
    }

    /***
     * @description: 0~6等概率转化为1-7等概率
     * @param:
     * @return: int
     * @author abner
     * @date: 2023/8/21 23:32
     */
    public static int g() {
        return f4() + 1;
    }



    /***
     * @description: x会以固定概率返回0和1
     * @param:
     * @return: int
     * @author abner
     * @date: 2023/8/21 23:35
     */
    public static int x() {
        return Math.random() < 0.84 ? 0 : 1;
    }

    /***
     * @description: 等概率返回0和1
     * @param:
     * @return: int
     * @author abner
     * @date: 2023/8/21 23:36
     */
    public static int y() {
        /**
         * 0概率p
         * 1概率1-p
         *
         * 01,或者10的时候才会是等概率
         */
        int ans = 0;
        do {
            ans = x();
        } while (ans == x());
        return ans;
    }



}
