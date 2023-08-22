package pers.guo.repositorytemplate.algo;


import java.util.Arrays;

/**
 * 对数期工具类
 * @author abner
 * @version 1.0
 * @createDate: 2023/8/22 09:43
 */
public class A_Comparator {

    /**
     * 随机生成数组（无序）
     * @param length
     * @param maxValue
     * @return int[]
     * @author guochao.bj@fang.com
     * @date 2023/8/22
     */
    public static int[] getRandomArray(int length,int maxValue){
        if (length <= 0 || maxValue <= 0) {
            throw new IllegalArgumentException("length or maxValue must be positive");
        }
        /**
         * Math.random() ->  [0,1) 所有的小数，等概率返回一个
         * Math.random() * N -> [0,N) 所有小数，等概率返回一个
         * (int)(Math.random() * N) -> [0,N-1] 所有的整数，等概率返回一个
         */
        int len = (int) (Math.random() * (length+1));

        int[] ints = new int[len];
        for (int i = 0; i < len; i++) {
            ints[i]=(int) ((maxValue + 1) * Math.random())
                    - (int) (maxValue * Math.random());
        }
        return ints;
    }

    /**
     *  随机生成有序数组
     * @param length
     * @param maxValue
     * @param shouldSort
     * @return int[]
     * @author guochao.bj@fang.com
     * @date 2023/8/22
     */
    public static int[] getRandomArray(int length,int maxValue,boolean shouldSort){
        int[] randomArray = getRandomArray(length, maxValue);
        if (shouldSort){
            Arrays.sort(randomArray);
        }
        return randomArray;
    }

    /**
     * 复制数据
     * @param arr
     * @return int[]
     * @author guochao.bj@fang.com
     * @date 2023/8/22
     */
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    /**
     * 打印数组
     * @param arr
     * @return void
     * @author guochao.bj@fang.com
     * @date 2023/8/22
     */
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    /**
     * 判断是否相等
     * @param arr1
     * @param arr2
     * @return boolean
     * @author guochao.bj@fang.com
     * @date 2023/8/22
     */
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if (arr1 == null || arr2 == null) {
            return arr1 == arr2;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 是否有序
     * @param arr
     * @return boolean
     * @author guochao.bj@fang.com
     * @date 2023/8/22
     */
    public static boolean isSorted(int[] arr) {
        if (arr.length < 2) {
            return true;
        }
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i]<max){
                return false;
            }
            max=Math.max(arr[i],max);
        }
        return true;
    }

    /**
     * 唯一的随机机制
     * @author guochao.bj@fang.com
     * @date 2023/8/22
     */
    public static class RandomBox{
        private final int min;
        private final int max;

        // 初始化时请一定不要让mi==ma
        public RandomBox(int mi, int ma) {
            if (mi==ma){
                throw new RuntimeException(new Exception("mi==ma"));
            }
            min = mi;
            max = ma;
        }

        /**
         * 13 ~ 17
         * 13 + [0,4]
         * @param
         * @return int
         * @author guochao.bj@fang.com
         * @date 2023/8/22
         */
        public int random(){
            return min+(int)(Math.random()*(max-min+1));
        }

        public int min() {
            return min;
        }

        public int max() {
            return max;
        }

    }








}
