package pers.guo.design.factory.method;

import pers.guo.design.strategy.City;
import pers.guo.design.strategy.Dimensionality;

/**
 * @author abner
 * @version 1.0
 * @description: 创建City 策略对象工厂
 * @date 2023/6/3 22:49
 */
public class CityDimensionFactoryMethod extends DimensionFactoryMethod{

    @Override
    public Dimensionality creatDimension() {
        return new City();
    }
}
