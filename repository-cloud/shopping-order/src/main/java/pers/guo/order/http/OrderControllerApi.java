package pers.guo.order.http;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: guochao.bj@fang.com
 * @createDate: 2023/4/19 20:53
 */
public interface OrderControllerApi {

    @GetMapping("/createOrder")
    public String createOrder(@RequestParam Integer id);
}
