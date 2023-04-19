package pers.guo.order.http;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.guo.order.feign.ProductCategoryControllerApi;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author: guochao.bj@fang.com
 * @createDate: 2023/4/19 20:54
 */
@RestController
@AllArgsConstructor
@RequestMapping("/order/manager")
public class OrderController implements OrderControllerApi {

    @Resource
    ProductCategoryControllerApi productCategoryControllerApi;

    @Override
    public String createOrder(Integer id) {
        String s = productCategoryControllerApi.pathVariableTest(id);
        System.out.println(s);
        String uuid = UUID.randomUUID().toString();
        System.out.println("订单服务【shopping-order】--使用"+id+"个商品创建订单.订单编号为："+uuid);
        return uuid;
    }
}
