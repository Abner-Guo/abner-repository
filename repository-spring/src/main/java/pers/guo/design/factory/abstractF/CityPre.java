package pers.guo.design.factory.abstractF;

import pers.guo.design.strategy.City;

/**
 * @author abner
 * @version 1.0
 * @description: 前置方法
 * @date 2023/6/3 23:12
 */
public class CityPre extends City {

    public void preVerify(){
        System.out.println("城市维度前置方法");
    }
}
