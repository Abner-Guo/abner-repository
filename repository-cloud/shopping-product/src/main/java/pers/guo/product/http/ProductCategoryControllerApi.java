package pers.guo.product.http;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: guochao.bj@fang.com
 * @createDate: 2023/4/19 20:39
 */
public interface ProductCategoryControllerApi {

    @RequestMapping(value = "/reduce",method = RequestMethod.GET)
    public String pathVariableTest(@RequestParam("id")  Integer id);

}
