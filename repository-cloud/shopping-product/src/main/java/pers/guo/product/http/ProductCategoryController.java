package pers.guo.product.http;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: guochao.bj@fang.com
 * @createDate: 2023/4/19 20:37
 */
@RestController
@RequestMapping("/goods/manager/productCategory")
public class ProductCategoryController implements ProductCategoryControllerApi {


    @Override
    public String pathVariableTest(Integer id) {
        return "商品服务【shopping-product】--减少库存"+id;
    }
}
