package pers.guo.design.factory.abstractF;

import pers.guo.design.strategy.Dimensionality;

/**
 * @author abner
 * @version 1.0
 * @description: TODO
 * @date 2023/6/3 23:31
 */
public class PostFactory {

    public Dimensionality getPostDimensionality(String dimension){
        Dimensionality dimensionality=null;
        if (dimension.equals("date")){
            dimensionality=new DatePost();
        }else if (dimension.equals("city")){
            dimensionality=new CityPost();
        }

        return dimensionality;
    }



}
