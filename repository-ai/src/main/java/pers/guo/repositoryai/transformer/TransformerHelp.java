package pers.guo.repositoryai.transformer;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

/**
 * 模拟Transformer 的组件Help
 * @author: guochao.bj@fang.com
 * @createDate: 2024/1/18 13:09
 */
public class TransformerHelp {


    public static void main(String[] args) {

        // 假设有3个数据点
        int numDataPoints = 3;

        // 创建一个2xN的INDArray，存储腰围和体重的对应关系
        INDArray waistWeightData = Nd4j.rand(new int[]{2, numDataPoints});

        // 打印表示腰围和体重对应关系的INDArray
        System.out.println("Waist and Weight Data:\n" + waistWeightData);


        INDArray rand = Nd4j.rand(2, 4);
        INDArray rand2 = Nd4j.rand(2, 4);
        //残差+层归一化
        System.out.println(sublayerConnection(rand,rand2));
    }


    /**
     * 残差连接,xxx可以是self-attention 也可以是前置神经网络
     * @param input xxx的输入
     * @param output xxx 的输出
     * @return org.nd4j.linalg.api.ndarray.INDArray
     * @author guochao.bj@fang.com
     * @date 2024/1/18
     */
    public static INDArray getNorm(INDArray input, INDArray output) {
        INDArray add = input.add(output);
        return relu(add);
    }


    /**
     * 实现ReLU激活函数
     * @param input
     * @return org.nd4j.linalg.api.ndarray.INDArray
     * @author guochao.bj@fang.com
     * @date 2024/1/18
     */
    public static INDArray relu(INDArray input) {
        return input.mul(input.gt(0.0));
    }


    /**
     * 对输入进行层归一化
     * @param input 输入数组
     * @param epsilon 用于避免除零的常数
     * @return 归一化后的数组
     * @author guochao.bj@fang.com
     */
    public static INDArray layerNorm(INDArray input, double epsilon) {

        INDArray mean = input.mean(true,-1);
        INDArray stdDev = input.std(false,true,-1);

        //添加一个小的常数（epsilon）以避免除零错误
        INDArray addi = stdDev.addi(epsilon);

        // 应用层归一化 (x减去均值，除以标准差)
        INDArray normalized = input.subi(mean)
                .divColumnVector(addi);
        return normalized;
    }


    /**
     * 残差和归一化 (Add ＆Norm)
     * @param input
     * @param output
     * @return org.nd4j.linalg.api.ndarray.INDArray
     * @author guochao.bj@fang.com
     * @date 2024/1/18
     */
    public static INDArray sublayerConnection (INDArray input, INDArray output){
        //残差
        INDArray norm = getNorm(input, output);

        //归一化
        INDArray layerNorm = layerNorm(norm, 1e-6);

        return layerNorm;
    }






}
