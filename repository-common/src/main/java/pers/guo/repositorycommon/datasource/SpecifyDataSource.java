package pers.guo.repositorycommon.datasource;

import java.lang.annotation.*;

/**
 * @description: 切换数据源注解
 * @author abner
 * @date 2022/12/2 19:11
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
@Documented
public @interface SpecifyDataSource {
    String value();
}
