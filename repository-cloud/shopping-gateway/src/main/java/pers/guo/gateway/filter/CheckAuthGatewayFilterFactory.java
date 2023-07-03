package pers.guo.gateway.filter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;


/**
 * 自定义局部过滤器<>针对单个路由，区别于全局过滤器</>
 *
 * 必须是一个Spring 的Bean
 * 使用反射识别 必须使用“GatewayFilterFactory”结尾
 * 必须继承抽象的 AbstractGatewayFilterFactory
 * 内部必须声明一个`Config` 静态内部类，声明属性来接收配置文件中的对应断言的信息
 * 需要结合shortcutFieldOrder 方法来进行绑定
 * 通过 apply 方法逻辑判断，返回true 断言成功
 *
 * @author: guochao.bj@fang.com
 * @createDate: 2023/7/3 18:56
 */
@Component
public class CheckAuthGatewayFilterFactory extends AbstractGatewayFilterFactory<CheckAuthGatewayFilterFactory.Config> {


    public CheckAuthGatewayFilterFactory() {
        super(CheckAuthGatewayFilterFactory.Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("value");
    }

    @Override
    public GatewayFilter apply(CheckAuthGatewayFilterFactory.Config config) {
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                String id=exchange.getRequest().getQueryParams().getFirst("id");

                //获取id参数
                //如果不等于value值失败
                if (StringUtils.isNotBlank(id)&&config.getValue().equals(id)){
                    //正常请求
                    chain.filter(exchange);
                }else {
                    //设置404 结束请求
                    exchange.getResponse().setStatusCode(HttpStatus.NOT_FOUND);
                    return exchange.getResponse().setComplete();
                }


               return chain.filter(exchange);
            }
        };
    }

    public static class Config {
        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

}