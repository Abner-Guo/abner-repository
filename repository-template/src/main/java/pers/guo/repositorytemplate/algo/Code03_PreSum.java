package pers.guo.repositorytemplate.algo;

/**
 * 前缀和
 * @author: guochao.bj@fang.com
 * @createDate: 2023/8/18 18:53
 */
public class Code03_PreSum {

    /**
     * 求连续内存空间，L到R的位置求和
     *
     * 数组：[1，2，3]
     *
     * 前缀数组：[1,3,6]
     *
     * 1～2位置数字和：
     *      0    ：1
     *      0,1,2    ：6
     *      1~2：6-1=5
     *
     */


    public static class RangeSum{

        int [] sumArray;

        public RangeSum(int [] array){
            sumArray = new int[array.length];
            sumArray[0]=array[0];
            for (int i = 1; i < array.length; i++) {
                sumArray[i]=sumArray[i-1]+array[i];
            }
        }

        public int rangeSum(int left,int right){
            return left==0?sumArray[right]:sumArray[right]-sumArray[left-1];
        }
    }


}
