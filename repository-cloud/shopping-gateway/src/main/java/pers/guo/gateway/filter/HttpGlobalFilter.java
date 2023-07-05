package pers.guo.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * gateway 全局过滤器
 *
 *
 * @author: guochao.bj@fang.com
 * @createDate: 2023/7/3 19:29
 */
@Component
public class HttpGlobalFilter implements GlobalFilter, Ordered {



    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        //输出一下请求地址
        System.out.println("【gateway 全局过滤器】"+exchange.getRequest().getPath().value());

        return chain.filter(exchange);
    }

    /**
     * 设置过滤器优先级
     * @author guochao.bj@fang.com
     * @date 2023/7/4
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
