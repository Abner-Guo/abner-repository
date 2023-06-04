package pers.guo.design.factory.method;

import pers.guo.design.strategy.Date;
import pers.guo.design.strategy.Dimensionality;

/**
 * @author abner
 * @version 1.0
 * @description: TODO
 * @date 2023/6/3 22:51
 */
public class DateDimensionFactoryMethod extends DimensionFactoryMethod {

    @Override
    public Dimensionality creatDimension() {
        return new Date();
    }
}
