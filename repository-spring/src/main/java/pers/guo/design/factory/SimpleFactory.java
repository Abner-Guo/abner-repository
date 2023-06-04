package pers.guo.design.factory;

import pers.guo.design.strategy.City;
import pers.guo.design.strategy.Date;
import pers.guo.design.strategy.Dimensionality;

import java.util.HashSet;

/**
 * @author abner
 * @version 1.0
 * @description: 简单工厂模式
 * @date 2023/6/3 21:53
 */
public class SimpleFactory {

    public static Dimensionality getDimension(String dimension){
        Dimensionality dimensionality = null;
        if (dimension.equals("date")){
            dimensionality=new Date();
        }else if (dimension.equals("city")){
            dimensionality=new City();
        }
        return dimensionality;
    }


    public static void main(String[] args) {

        Dimensionality dimension = SimpleFactory.getDimension("date");
        /**
         * 这里可以看出来，工厂模式只关注于对象的创建
         * 这个对象（策略）处理数据，要使用内部哪个方法不做关心
         * dimension 对象调用supplementSet还是getAllDimension方法，使用者自行选择
         */
        dimension.supplementSet(new HashSet<>());

    }


}
