package pers.guo.repositoryai.transformer.model;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.ops.transforms.Transforms;

/**
 *
 * 残差和归一化 (Add ＆Norm)
 * @author: guochao.bj@fang.com
 * @createDate: 2024/1/19 12:51
 */
public class AddNorm {


    /**
     * 残差连接,xxx可以是self-attention 也可以是前置神经网络
     * @param input xxx的输入
     * @param output xxx 的输出
     * @return org.nd4j.linalg.api.ndarray.INDArray
     * @author guochao.bj@fang.comshehu
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
        return Transforms.relu(input);
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


    public static void main(String[] args) {
        // 创建一个2xN的INDArray，存储腰围和体重的对应关系

        INDArray matrix = Nd4j.create(new double[][] {
                {0.0564, -0.1516, 0.0336},
                {-0.0564, 0.1516, -0.0336}
        });

        System.out.println(matrix.transpose());

        INDArray mean = matrix.mean(true,-1);
        INDArray stdDev = matrix.std(false,true,-1);
        INDArray xxx = matrix.subi(mean);
        INDArray yyy = stdDev.addi(1e-6);

        System.out.println(mean);
        System.out.println("--------------");
        System.out.println(stdDev);

        System.out.println("--------------");
        System.out.println(xxx);

        System.out.println("--------------");
        System.out.println(yyy);

        System.out.println("--------------");
        INDArray indArray = xxx.divColumnVector(yyy);
        System.out.println(indArray);

        System.out.println("--------------");
        System.out.println(sublayerConnection(matrix,matrix));
    }

}
