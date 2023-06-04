package pers.guo.design.factory.abstractF;

import pers.guo.design.strategy.City;

/**
 * @author abner
 * @version 1.0
 * @description: TODO
 * @date 2023/6/3 23:15
 */
public class CityPost extends City {

    public void postVerify(){
        System.out.println("城市维度后置方法");
    }

}
