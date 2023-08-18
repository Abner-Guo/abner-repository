package pers.guo.repositorytemplate.algo;

/**
 * @author: guochao.bj@fang.com
 * @createDate: 2023/8/18 10:46
 */
public class Code02_Sort {

    /**
     *
     * 1.先考虑边界条件
     * 2.范围
     * 3.选择排序：先确定的是第一位 (每次交换都是开始的位置a和后面的位置b交换，有可能b-a>1)
     * 4.冒泡排序：先确定最后一位(每次交换都是开始的位置a和后面的位置b交换，两两交换b-a=1)
     * 5.插入排序：从未排序部分取出一个元素，将它与已排序部分的元素逐个比较，找到合适的位置并插入
     */

    public static void main(String[] args) {
        int [] test= {3,8,5,4,1,2};
        //print(selectSort(test));
        //print(bubbleSort(test));
        print(insterSort(test));
    }

    /**
     * 选择排序
     * @param array
     * @return int[]
     * @author guochao.bj@fang.com
     * @date 2023/8/18
     */
    public static int[] selectSort(int [] array){
        //先确认边界：在数组为null 或者数组长度小于2的时候，无需排序
        if (array==null||array.length==1){
            return array;
        }
        /**
         *
         * [3,8,5,4,1,2]
         *
         * 比较范围：0~length-1
         * 当前位置index=0
         * 拿3和后面的(1~length-1)比较，谁小，放到0
         * 取到最小数的index=4
         * [1,8,5,4,3,2]
         *
         * 比较范围：1~length-1
         * index=1
         * 拿8和后面的（2~length-1）比较，谁小，放到1
         * 取到最小数的index=4
         * [1,2,5,4,3,8]
         *
         */

        for (int i = 0; i < array.length; i++) {
            //外层循环：确定比较范围
            int minValueIndex=i;
            for (int j = i+1; j < array.length; j++) {
                //取最小的数的index
                minValueIndex=array[minValueIndex]>array[j]?j:minValueIndex;
            }
            swap(i,minValueIndex,array);
        }
        return array;
    }

    /**
     * 冒泡排序
     * @param array
     * @return int[]
     * @author guochao.bj@fang.com
     * @date 2023/8/18
     */
    public static int[] bubbleSort(int[] array){
        //先确认边界：在数组为null 或者数组长度小于2的时候，无需排序
        if (array==null||array.length==1){
            return array;
        }

        /**
         *
         * [3,8,5,4,1,2]
         *
         * 两两比较，谁比较大，去后面
         *
         * 第一轮比较,比较范围：0~length-1
         * 3vs8 ->[3,8,5,4,1,2]
         * 8vs5 ->[3,5,8,4,1,2]
         * 8vs4 ->[3,5,4,8,1,2]
         * ...
         * 8vs2 ->[3,5,4,1,2,8]
         *
         * 第二轮比较，比较范围：0~length-2
         * 3vs5 ->[3,5,4,1,2,8]
         * 5vs4 ->[3,4,5,1,2,8]
         * ...
         * 5vs2 ->[3,4,1,2,5,8]
         *
         */
        int end = array.length;
        for (int i = end-1; i >= 0; i--) {
            //外层循环：确定比较范围
            for (int j = 1; j <= i; j++) {
                if (array[j-1]>array[j]){
                    swap(j-1,j,array);
                }
            }
        }

        return array;
    }


    /**
     * 插入排序
     * @param array
     * @return int[]
     * @author guochao.bj@fang.com
     * @date 2023/8/18
     */
    public static int[] insterSort(int[] array){
        //先确认边界：在数组为null 或者数组长度小于2的时候，无需排序
        if (array==null||array.length==1){
            return array;
        }

        /**
         * [3,8,5,4,1,2]
         * 从未排序部分取出一个元素，将它与已排序部分的元素逐个比较，找到合适的位置并插入
         * 
         * 比较范围：0~1
         * [3,8,5,4,1,2]
         * 比较范围：0~2
         * [3,5,8,4,1,2]
         * 比较范围：0~3
         * [3,4,5,8,1,2]
         */

        for (int i = 1; i < array.length; i++) {
            //外层循环确认范围
            for (int j = 0; j <i ; j++) {
                //拿未排序的array[i] 和已排序的0~j 比较
                if (array[i]<array[j]){
                    swap(j,i,array);
                }
            }
        }
        return array;
    }







    /**
     * 打印数组
     * @author guochao.bj@fang.com
     * @date 2023/8/18
     */
    public static void print(int[] array){
        for (int i : array) {
            System.out.print(i+" ");
        }
        System.out.println();
    }

    /**
     * 数组交换位置
     * @param i
     * @param j
     * @param array
     * @return void
     * @author guochao.bj@fang.com
     * @date 2023/8/18
     */
    public static void swap (int i,int j,int[] array){
        int temp=array[i];
        array[i]=array[j];
        array[j]=temp;
    }





}
