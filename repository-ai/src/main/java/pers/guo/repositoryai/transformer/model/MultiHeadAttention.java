package pers.guo.repositoryai.transformer.model;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.ops.transforms.Transforms;

/**
 * 多头注意力机制
 * @author: guochao.bj@fang.com
 * @createDate: 2024/1/19 12:54
 */
public class MultiHeadAttention {

    private INDArray Wq;
    private INDArray Wk;
    private INDArray Wv;

    public MultiHeadAttention(INDArray wq, INDArray wk, INDArray wv) {
        //三个权重都是在模型训练阶段学习来的，这里在每个头初始化的设置
        Wq = wq;
        Wk = wk;
        Wv = wv;
    }

    public INDArray getWq() {
        return Wq;
    }

    public INDArray getWk() {
        return Wk;
    }

    public INDArray getWv() {
        return Wv;
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
        if (isMask) {
            //mask其实是为了模型在训练阶段使用，使用阶段此处不处理
        }

        // 对注意力分数进行 softmax 操作
        INDArray selfAttn = Transforms.softmax(scores);
        // 返回加权后的 value 和 self-attention 分数
        return selfAttn.mmul(value);
    }


    /***
     * @description:  多头注意力机制
     * @param: input   输入的张量
     * @param: isMask   是否使用掩码
     * @return: org.nd4j.linalg.api.ndarray.INDArray
     * @author abner
     * @date: 2024/1/19 23:12
     */
    public INDArray multiHeadAttention(INDArray input, Boolean isMask) {
        // 对输入张量进行矩阵乘法操作，得到Q
        INDArray Q = input.mmul(this.getWq());
        // 对输入张量进行矩阵乘法操作，得到K
        INDArray K = input.mmul(this.getWk());
        // 对输入张量进行矩阵乘法操作，得到V
        INDArray V = input.mmul(this.getWv());

        // 调用自注意力方法，传入是否使用掩码、Q、K、V
        return selfAttention(isMask, Q, K, V);
    }


    public static void main(String[] args) {

        INDArray input = Nd4j.create(new double[][]{{1.0, 2.0},{2.0,4.0}});
        INDArray out = Nd4j.create(new double[][]{{1.0, 0},{0,1.0}});
        System.out.println(out);
        System.out.println(input.mmul(out));
        INDArray scaleFactor = Nd4j.create(new double[][]{{0.5, 0},{0,0.5}});
        System.out.println(input.mmul(scaleFactor));

        MultiHeadAttention multiHeadAttention = new MultiHeadAttention(scaleFactor, out, out);
        System.out.println(multiHeadAttention.multiHeadAttention(input, false));


    }


}
