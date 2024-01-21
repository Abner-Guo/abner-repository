package pers.guo.repositoryai.transformer.model;

import org.nd4j.linalg.api.ndarray.INDArray;
import pers.guo.repositoryai.transformer.TransformerHelp;

/**
 * @author abner
 * @version 1.0
 * @description: 解码器
 * @date 2024/1/20 09:07
 */
public class Decoder {

    private Integer h=6;

    private INDArray encoderKey;

    private INDArray encoderValue;


    public Decoder(Integer h, INDArray encoderKey, INDArray encoderValue) {
        this.h = h;
        this.encoderKey = encoderKey;
        this.encoderValue = encoderValue;
    }

    /***
     * @description:  解码器
     * @param: input 解码器输入
     * @param: heads   多头
     * @return: org.nd4j.linalg.api.ndarray.INDArray
     * @author abner
     * @date: 2024/1/20 09:30
     */
    public INDArray getDecoderOutput(INDArray input,int heads){
        INDArray decoderLayerInput=null;
        INDArray decoderLayerOutput=null;
        for (Integer i = 0; i < this.h; i++) {
            if (decoderLayerOutput==null){
                decoderLayerInput=input;
            }else {
                decoderLayerInput=decoderLayerOutput;
            }
            DecoderLayer decoderLayer = new DecoderLayer(heads,encoderKey,encoderValue);
            decoderLayerOutput = decoderLayer.decoderLayer(decoderLayerInput);
        }
        return decoderLayerOutput;
    }




}

/***
 * @description: 解码器层
 * @author abner
 * @date: 2024/1/20 09:08
 */
class DecoderLayer {

    private int heads=8;

    private INDArray encoderKey;

    private INDArray encoderValue;

    public DecoderLayer(int heads, INDArray encoderKey, INDArray encoderValue) {
        this.heads = heads;
        this.encoderKey = encoderKey;
        this.encoderValue = encoderValue;
    }

    /***
     * @description: Encoder-Decoder Attention 
     * @param: Q 来自编码器-mask注意力，K，V都来自编码器
     * @return: org.nd4j.linalg.api.ndarray.INDArray
     * @author abner
     * @date: 2024/1/20 09:12
     */
    public INDArray encoderDecoderAttention(INDArray Q){
        return MultiHeadAttention.selfAttention(false, Q, encoderKey, encoderValue);
    }
    
    
    /*** 
     * @description: 解码器层解码：包括 Masked Multi-Head Attention，Encoder-Decoder Attention
     * @param: input 
     * @return: org.nd4j.linalg.api.ndarray.INDArray 
     * @author abner
     * @date: 2024/1/20 09:25
     */ 
    public INDArray decoderLayer(INDArray input){
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
            INDArray multiHeadAttentionOutput = multiHeadAttention.multiHeadAttention(input, true);
            //结果concat
            if (i==0){
                attentionOutput=multiHeadAttentionOutput;
            }else {
                attentionOutput=attentionOutput.addi(multiHeadAttentionOutput);
            }
        }

        //add&norm
        INDArray sublayerConnectionOne = AddNorm.sublayerConnection(input, attentionOutput);
        
        //Encoder-Decoder Attention
        INDArray encoderDecoderAttention = encoderDecoderAttention(sublayerConnectionOne);

        //add&norm
        INDArray encoderDecoderAttentionOutput = AddNorm.sublayerConnection(input, encoderDecoderAttention);
        
        //前馈神经网络
        INDArray feedForwardOutput = new FeedForward(TransformerHelp.oneHot, TransformerHelp.twoHot).feedForward(encoderDecoderAttentionOutput);

        //add&norm
        INDArray sublayerConnection = AddNorm.sublayerConnection(sublayerConnectionOne, feedForwardOutput);
        
        return sublayerConnection;
    }
    
    
}
