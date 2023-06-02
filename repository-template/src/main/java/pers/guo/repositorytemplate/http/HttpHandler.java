package pers.guo.repositorytemplate.http;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * OOM测试设置
 * @author: guochao.bj@fang.com
 * @createDate: 2023/5/31 10:36
 */
@RestController("action")
public class HttpHandler {

    @PostMapping("/get")
    public String get() {
        return "get";
    }

}
