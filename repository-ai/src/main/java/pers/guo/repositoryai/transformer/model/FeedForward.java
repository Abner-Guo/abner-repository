package pers.guo.repositoryai.transformer.model;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.ops.transforms.Transforms;

/**
 * @author abner
 * @version 1.0
 * @description: 前馈神经网络
 * @date 2024/1/19 23:19
 */
public class FeedForward {

    private INDArray w1;
    private INDArray w2;

    public FeedForward(INDArray w1, INDArray w2) {
        this.w1 = w1;
        this.w2 = w2;
    }

    public INDArray getW1() {
        return w1;
    }

    public INDArray getW2() {
        return w2;
    }

    /*** 
     * @description: 线性层
     * @param: input
     * @param: w
     * @return: org.nd4j.linalg.api.ndarray.INDArray 
     * @author abner
     * @date: 2024/1/20 00:07
     */ 
    public INDArray linear(INDArray input, INDArray w){
        return input.mmul(w);
    }

    /***
     * @description: 前馈神经网络
     * @param: input
     * @return: org.nd4j.linalg.api.ndarray.INDArray
     * @author abner
     * @date: 2024/1/20 00:11
     */
    public INDArray feedForward(INDArray input){

        INDArray wiLinear = linear(input, w1);
        INDArray relu = Transforms.relu(wiLinear);
        System.out.println(relu);
        return linear(relu, w2);
    }



    public static void main(String[] args) {
        INDArray out = Nd4j.create(new double[][]{{1.0, 0},{0,1.0}});
        FeedForward feedForward = new FeedForward(out, out);
        INDArray matrix = Nd4j.create(new double[][] {
                {0.0564, -0.1516},
                {-0.0564, 0.1516}
        });
        System.out.println(feedForward.feedForward(matrix));
    }
}
