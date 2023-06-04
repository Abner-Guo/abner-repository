package pers.guo.design.factory.abstractF;

import pers.guo.design.strategy.Date;

/**
 * @author abner
 * @version 1.0
 * @description: TODO
 * @date 2023/6/3 23:18
 */
public class DatePre extends Date {

    public void preVerify(){
        System.out.println("时间维度前置方法");
    }

}
