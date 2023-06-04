package pers.guo.design.factory.abstractF;

import pers.guo.design.strategy.Dimensionality;

import java.util.HashSet;

/**
 * @author abner
 * @version 1.0
 * @description: TODO
 * @date 2023/6/3 23:33
 */
public class AbstractMain {

    public static void main(String[] args) {

        PreFactory preFactory = new PreFactory();
        PostFactory postFactory = new PostFactory();

        Dimensionality city = preFactory.getPreDimensionality("city");
        Dimensionality date = postFactory.getPostDimensionality("date");

        CityPre cityPre = (CityPre) city;
        DatePost datePost = (DatePost) date;

        cityPre.preVerify();
        cityPre.getAllDimension(null);
        datePost.postVerify();
        datePost.supplementSet(new HashSet<>());

    }

}
