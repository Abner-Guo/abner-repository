package pers.guo.repositoryai.transformer;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import pers.guo.repositoryai.transformer.model.Decoder;
import pers.guo.repositoryai.transformer.model.Encoder;
import pers.guo.repositoryai.transformer.model.Precoding;

/**
 * 模拟Transformer 的组件Help
 * @author: guochao.bj@fang.com
 * @createDate: 2024/1/18 13:09
 */
public class TransformerHelp {


    public static INDArray oneHot=Nd4j.create(new double[][]{{1.0, 0},{0,1.0}});
    public static INDArray twoHot=Nd4j.create(new double[][]{{0.5, 0},{0,0.5}});

    /***
     * @description: 组装自定义Transformer
     * @param: input 文本输入
     * @return: java.lang.String 文本输出
     * @author abner
     * @date: 2024/1/20 09:36
     */
    public static String ABDTransformer(String input){
        //token 化，位置编码
        INDArray tokenization = Precoding.tokenization(input);
        INDArray positionEncoding = Precoding.positionEncoding(tokenization);
        //编码器(论文默认使用6层，8头编码器)
        Encoder encoder = new Encoder(6);
        INDArray encoderOutput = encoder.getEncoderOutput(positionEncoding,8);
        //解码器  (论文默认6层，编码器器的输出作为解码器的K,V)
        Decoder decoder = new Decoder(6,encoderOutput,encoderOutput);

        //从<start>，直到<end>结束
        StringBuffer outputBuffer = new StringBuffer();
        String output = " ";
        String decoderInput = "<start>";
        while (!output.equals("<end>")){
            INDArray tokenizationDecoderInput= Precoding.tokenization(decoderInput);
            INDArray positionEncodingDecoderInput = Precoding.positionEncoding(tokenizationDecoderInput);
            INDArray decoderOutput = decoder.getDecoderOutput(positionEncodingDecoderInput, 8);
            //词表转换

            //概率猜测


            if (output.equals("<end>")){
                return outputBuffer.toString();
            }
        }
        return null;
    }


    public static void main(String[] args) {

    }



}
