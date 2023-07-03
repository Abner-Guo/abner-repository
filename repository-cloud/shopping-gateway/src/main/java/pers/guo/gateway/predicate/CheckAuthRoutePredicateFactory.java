package pers.guo.gateway.predicate;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * 自定义gateway 断言工厂
 *
 * 必须是一个Spring 的Bean
 * 使用反射识别 必须使用“RoutePredicateFactory”结尾
 * 必须继承抽象的 AbstractRoutePredicateFactory
 * 内部必须声明一个`Config` 静态内部类，声明属性来接收配置文件中的对应断言的信息
 * 需要结合shortcutFieldOrder 方法来进行绑定
 * 通过 apply 方法逻辑判断，返回true 断言成功
 *
 * @author: guochao.bj@fang.com
 * @createDate: 2023/7/3 18:23
 */
@Component
public class CheckAuthRoutePredicateFactory extends AbstractRoutePredicateFactory<CheckAuthRoutePredicateFactory.Config> {



    public CheckAuthRoutePredicateFactory() {
        super(CheckAuthRoutePredicateFactory.Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        //获取config 中的属性数据
        return Arrays.asList("name");
    }

    @Override
    public Predicate<ServerWebExchange> apply(CheckAuthRoutePredicateFactory.Config config) {
        return new GatewayPredicate() {
            @Override
            public boolean test(ServerWebExchange exchange) {
                if (config.getName().equals("abner")){
                    return true;
                }
                return false;
            }
        };
    }

    /**
     * 声明属性来接收配置文件中的对应断言的信息
     * @author guochao.bj@fang.com
     * @date 2023/7/3
     */
    @Validated
    public static class Config {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
