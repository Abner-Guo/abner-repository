package pers.guo.design.strategy;

import java.util.Set;

/**
 * @author abner
 * @version 1.0
 * @description: 处理维度类
 * @date 2023/6/3 22:06
 */
public class HandleDimension {


    private Dimensionality dimensionality;


    public HandleDimension(Dimensionality dimensionality) {
        this.dimensionality = dimensionality;
    }


    public Set<String> handleDimension (Set<String> oldSet){
       return dimensionality.supplementSet(oldSet);
    }

}
