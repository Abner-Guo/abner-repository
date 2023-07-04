package pers.guo.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

/**
 * gateway 跨域配置
 * @author: guochao.bj@fang.com
 * @createDate: 2023/7/4 10:54
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsWebFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedMethod("*");
        //可以允许来自 http://docs.spring.io 的get请求方式获取服务数据。
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
        //对所有访问到网关服务器的请求地址
        source.registerCorsConfiguration("/**", config);

        return new CorsWebFilter(source);
    }

}
