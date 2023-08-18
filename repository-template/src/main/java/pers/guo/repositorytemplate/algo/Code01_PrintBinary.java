package pers.guo.repositorytemplate.algo;

/**
 * @author: guochao.bj@fang.com
 * @createDate: 2023/8/17 18:36
 */
public class Code01_PrintBinary {

    /**
     * 1.二进制打印整数，Int 是32位
     * 2.位运算 ，乘2是左移1位
     * 3.为什么范围是-2^31 到 2^31-1
     * 4.二进制取反~5
     * 5.负数二进制是32位符号位，剩下的31位取反加1
     * 6.正数取反+1 是负数
     * 5.最小的负数，取相反数还是本身
     */

    public static void point(int num){

        for (int i = 31; i >=0 ; i--) {
            System.out.print((num&(1<<i))==0?"0":"1");
        }
        System.out.println();
    }


    public static void main(String[] args) {
        point(6);
        point(~6);
        point(~6+1);
        point(-6);
    }


}
