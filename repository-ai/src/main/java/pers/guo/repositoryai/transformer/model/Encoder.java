package pers.guo.repositoryai.transformer.model;

import org.nd4j.linalg.api.ndarray.INDArray;
import pers.guo.repositoryai.transformer.TransformerHelp;

import java.util.List;

/**
 * @author abner
 * @version 1.0
 * @description: 编码器
 * @date 2024/1/20 00:37
 */
public class Encoder {

    private Integer h=6;

    public Encoder(Integer h) {
        this.h = h;
    }

    /***
     * @description:  获取编码器输出
     * @param: input
     * @param: heads 多头
     * @return: org.nd4j.linalg.api.ndarray.INDArray
     * @author abner
     * @date: 2024/1/20 01:26
     */
    public INDArray getEncoderOutput(INDArray input,int heads){
        INDArray encoderLayerInput=null;
        INDArray encoderLayerOutput=null;
        for (Integer i = 0; i < this.h; i++) {
            if (encoderLayerOutput==null){
                encoderLayerInput=input;
            }else {
                encoderLayerInput=encoderLayerOutput;
            }
            EncoderLayer encoderLayer = new EncoderLayer(heads);
            encoderLayerOutput = encoderLayer.encoderLayer(encoderLayerInput);
        }
        return encoderLayerOutput;
    }




}
/***
 * @description: 编码器层
 * @author abner
 * @date: 2024/1/20 00:40
 */
class EncoderLayer{

    private int heads=8;

    public EncoderLayer(int heads) {
        this.heads = heads;
    }

    public int getHeads() {
        return heads;
    }



    public INDArray encoderLayer(INDArray input){
        INDArray attentionOutput=null;
        //多头处理器
        for (int i = 0; i < this.heads; i++) {
            //初始化多头处理器
            MultiHeadAttention multiHeadAttention=null;
            if(i/2==0){
                multiHeadAttention = new MultiHeadAttention(TransformerHelp.oneHot, TransformerHelp.twoHot, TransformerHelp.twoHot);
            }else {
                multiHeadAttention = new MultiHeadAttention(TransformerHelp.twoHot, TransformerHelp.oneHot, TransformerHelp.oneHot);
            }
            INDArray multiHeadAttentionOutput = multiHeadAttention.multiHeadAttention(input, false);
            //结果concat
            if (i==0){
                attentionOutput=multiHeadAttentionOutput;
            }else {
                attentionOutput=attentionOutput.addi(multiHeadAttentionOutput);
            }
        }
        //add&norm
        INDArray sublayerConnectionOne = AddNorm.sublayerConnection(input, attentionOutput);

        //前馈神经网络
        INDArray feedForwardOutput = new FeedForward(TransformerHelp.oneHot, TransformerHelp.twoHot).feedForward(sublayerConnectionOne);

        //add&norm
        INDArray sublayerConnection = AddNorm.sublayerConnection(sublayerConnectionOne, feedForwardOutput);

        return sublayerConnection;
    }



}

