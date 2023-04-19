package pers.guo.user;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: guochao.bj@fang.com
 * @createDate: 2023/4/19 20:20
 */
@RefreshScope  //开启动态读取配置
@RestController
@RequestMapping("/api")
public class ConfigController {

    @Value("${shopping.user.name}")
    private String name;

    @Value("${shopping.user.age}")
    private Integer age;

    @RequestMapping("/info")
    public String getInfo() {
        return name+" is "+age;
    }


}
