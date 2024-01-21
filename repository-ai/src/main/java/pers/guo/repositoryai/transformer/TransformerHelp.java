package pers.guo.repositoryai.transformer;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

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


        //编码器

        //解码器

        //词表转换

        //概率猜测



        return null;
    }


    public static void main(String[] args) {

    }



}
