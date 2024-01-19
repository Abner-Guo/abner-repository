package pers.guo.repositoryai.transformer.model;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.ops.transforms.Transforms;

/**
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

        // 对注意力分数进行 softmax 操作
        INDArray selfAttn = Transforms.softmax(scores);
        // 返回加权后的 value 和 self-attention 分数
        return selfAttn.mmul(value);
    }


    public static void main(String[] args) {

        INDArray input = Nd4j.create(new double[]{1.0, 2.0, 3.0});

        System.out.println(selfAttention(true, input, input, input));

    }


}
