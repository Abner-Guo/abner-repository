package pers.guo.repositorytemplate.algo;

import javax.management.RuntimeErrorException;

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

        public int[] getSumArray() {
            return sumArray;
        }
    }


    private static int [] getRandArray(int length,int maxValue){

        if (length==0||maxValue==0){
            throw new RuntimeErrorException(new Error("length or maxValue =< 0"));
        }

        int[] ints = new int[(int) ((length + 1) * Math.random())];
        for (int i = 0; i < length; i++) {

            ints[i]=(int) (Math.random()*maxValue);
        }
        return ints;
    }

    private static void print(int[] array){
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]+" ");
        }
    }


    public static void main(String[] args) {
        int[] randArray = getRandArray(10, 10);
        print(randArray);
        System.out.println();
        RangeSum rangeSum = new RangeSum(randArray);
        print(rangeSum.getSumArray());
        System.out.println();
        System.out.println(rangeSum.rangeSum(2, 5));

    }



}
