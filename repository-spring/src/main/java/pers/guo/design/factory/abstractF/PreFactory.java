package pers.guo.design.factory.abstractF;

import pers.guo.design.strategy.Dimensionality;

/**
 * @author abner
 * @version 1.0
 * @description: TODO
 * @date 2023/6/3 23:22
 */
public class PreFactory {

    public Dimensionality getPreDimensionality(String dimension){
        Dimensionality dimensionality=null;
        if (dimension.equals("date")){
            dimensionality=new DatePre();
        }else if (dimension.equals("city")){
            dimensionality=new CityPre();
        }

        return dimensionality;
    }


}
