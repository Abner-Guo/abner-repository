package pers.guo.repositorycommon.datasource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * @author abner
 * @version 1.0
 * @description: 切换数据--切面
 * @date 2022/12/2 19:14
 */
@Aspect
@Component
public class DataSourceAspect implements Ordered {

    /**
     * 默认数据源
     * */
    public static final String DEFAULT_DATASOURCE = "dataSource";


    /**
     * 选取aop切入点为注解
     */
    @Pointcut("@annotation(pers.guo.repositorycommon.datasource.SpecifyDataSource)")
    public void DataSourceAspect() {
    }

    /** 
     * @description: aop切入的时候都会触发此方法，切换数据源 
     * @param: pjp 
     * @return: java.lang.Object 
     * @author abner
     * @date: 2022/12/2 21:05
     */ 
    @Around("DataSourceAspect()")
    public Object deAround(ProceedingJoinPoint pjp) throws Throwable {
        Object result = null;
        if (pjp.getSignature() instanceof MethodSignature) {
            MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
            SpecifyDataSource multiDs = methodSignature.getMethod().getAnnotation(SpecifyDataSource.class);
            if (multiDs != null) {
                DynamicDataSource.setDataSourceKey(multiDs.value());
                try {
                    result = pjp.proceed();
                } finally {
                    DynamicDataSource.setDataSourceKey(DEFAULT_DATASOURCE);
                }
            } else {
                result = pjp.proceed();
            }
        } else {
            result = pjp.proceed();
        }
        return result;

    }



    @Override
    public int getOrder() {    return 0;}


}
