package pers.guo.design.factory.abstractF;

import pers.guo.design.strategy.Date;

/**
 * @author abner
 * @version 1.0
 * @description: TODO
 * @date 2023/6/3 23:17
 */
public class DatePost extends Date {

    public void postVerify(){
        System.out.println("时间维度后置方法");
    }

}
