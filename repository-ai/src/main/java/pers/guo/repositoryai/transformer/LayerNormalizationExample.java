package pers.guo.repositoryai.transformer;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

/**
 * @author: guochao.bj@fang.com
 * @createDate: 2024/1/18 18:30
 */
public class LayerNormalizationExample {


    public static void main(String[] args) {

        // 创建一个2xN的INDArray，存储腰围和体重的对应关系

        INDArray matrix = Nd4j.create(new double[][] {
                {0.0564, -0.1516, 0.0336},
                {-0.0564, 0.1516, -0.0336}
        });
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
        INDArray indArray = xxx
                .divColumnVector(yyy);



        System.out.println(indArray);

    }

}
