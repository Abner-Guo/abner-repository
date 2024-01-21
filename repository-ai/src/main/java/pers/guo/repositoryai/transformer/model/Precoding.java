package pers.guo.repositoryai.transformer.model;

import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.SegToken;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author abner
 * @version 1.0
 * @description: 预编码（token化，位置编码）
 * @date 2024/1/20 21:04
 */
public class Precoding {


    private static Map<String, INDArray> wordVectors=new HashMap<>();

    static {
        // 将每个单词映射到一个随机向量
        wordVectors.put("人工智能", Nd4j.rand(1, 2)); // 随机生成一个大小为 1x10 的向量
        wordVectors.put("在", Nd4j.rand(1, 2));
        wordVectors.put("房地产", Nd4j.rand(1, 2));
        wordVectors.put("公司", Nd4j.rand(1, 2));
        wordVectors.put("的", Nd4j.rand(1, 2));
        wordVectors.put("使用", Nd4j.rand(1, 2));
        wordVectors.put("前景", Nd4j.rand(1, 2));
    }


    /*** 
     * @description: 将文本转化为向量 
     * @param: text 
     * @return: org.nd4j.linalg.api.ndarray.INDArray 
     * @author abner
     * @date: 2024/1/20 21:10
     */ 
    public static INDArray tokenization(String text) {

        JiebaSegmenter segmenter = new JiebaSegmenter();
        List<SegToken> segTokens = segmenter.process(text, JiebaSegmenter.SegMode.SEARCH);

        // 提取分词结果
        List<String> words = new ArrayList<>();
        for (SegToken segToken : segTokens) {
            words.add(segToken.word);
        }

        // 初始化一个二维数组，每个单词对应一个向量
        long vectorSize = wordVectors.values().iterator().next().length();
        INDArray indArray = Nd4j.create(words.size(), vectorSize);

        // 将每个单词的向量添加到数组中
        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            if (wordVectors.containsKey(word)) {
                indArray.putRow(i, wordVectors.get(word));
            } else {
                // 处理未知单词的情况，可以根据实际需求进行调整
                // 在这里，我们简单地使用零向量表示未知单词
                indArray.putRow(i, Nd4j.zeros(1, vectorSize));
            }
        }
        return indArray;
    }

    /*** 
     * @description:  位置编码
     * PE(pos,2i/2i+1) = sin/cos(pos/10000^{2i/d_{model}})
     * @param: input 
     * @return: org.nd4j.linalg.api.ndarray.INDArray 
     * @author abner
     * @date: 2024/1/21 11:37
     */ 
    public static INDArray positionEncoding(INDArray input) {




        return null;
    }





    public static void main(String[] args) {
        String text = "人工智能在房地产公司的使用前景";
        JiebaSegmenter segmenter = new JiebaSegmenter();
        List<SegToken> segTokens = segmenter.process(text, JiebaSegmenter.SegMode.SEARCH);

        // 提取分词结果
        List<String> words = new ArrayList<>();
        for (SegToken segToken : segTokens) {
            words.add(segToken.word);
        }

        System.out.println(words);

        System.out.println(tokenization("人工智能在房地产公司的使用前景"));
        System.out.println(Nd4j.rand(2,3));

    }


}
