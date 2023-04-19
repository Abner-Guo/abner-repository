package pers.guo.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: guochao.bj@fang.com
 * @createDate: 2023/4/19 20:48
 */
@FeignClient(value = "shopping-product")
public interface ProductCategoryControllerApi {

    @RequestMapping(value = "/goods/manager/productCategory/reduce",method = RequestMethod.GET)
    public String pathVariableTest(@RequestParam("id") Integer id);

}
