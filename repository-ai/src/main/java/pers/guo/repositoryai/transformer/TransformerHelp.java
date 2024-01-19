package pers.guo.repositoryai.transformer;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.ops.transforms.Transforms;

/**
 * 模拟Transformer 的组件Help
 * @author: guochao.bj@fang.com
 * @createDate: 2024/1/18 13:09
 */
public class TransformerHelp {


    public static INDArray oneHot=Nd4j.create(new double[][]{{1.0, 0},{0,1.0}});
    public static INDArray twoHot=Nd4j.create(new double[][]{{0.5, 0},{0,0.5}});




    public static void main(String[] args) {

        System.out.println(oneHot.c(twoHot));


        // 假设有3个数据点
        int numDataPoints = 3;

        // 创建一个2xN的INDArray，存储腰围和体重的对应关系
        INDArray waistWeightData = Nd4j.rand(new int[]{2, numDataPoints});

        // 打印表示腰围和体重对应关系的INDArray
        System.out.println("Waist and Weight Data:\n" + waistWeightData);

        // 示例数据
        INDArray input = Nd4j.create(new double[][]{{1.0, 2.0},{2.0,4.0}});

        // 调用 softmax 函数
        INDArray result = Transforms.softmax(input);

        // 打印结果
        System.out.println("Input:");
        System.out.println(input);
        System.out.println("Softmax Output:");
        System.out.println(result);

        System.out.println("selfAttention:");
        System.out.println(selfAttention(false,input,input,input));

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


    /**
     * 自注意力机制 （Q*k）*V
     * @param isMask 是否掩码操作
     * @param query 查询数组
     * @param key
     * @param value
     * @return org.nd4j.linalg.api.ndarray.INDArray
     * @author guochao.bj@fang.com
     * @date 2024/1/19
     */
      public static INDArray selfAttention(Boolean isMask, INDArray query, INDArray key, INDArray value){
          long dimSize = query.size(-1);
          // 计算注意力分数
          INDArray scores = query.mmul(key).div(Math.sqrt(dimSize));

          // 在 QK 之后，softmax 之前应用掩码（此处省略掩码操作）

          // 对注意力分数进行 softmax 操作
          INDArray selfAttn = Transforms.softmax(scores);
          // 返回加权后的 value 和 self-attention 分数
          return selfAttn.mmul(value);
      }


}
