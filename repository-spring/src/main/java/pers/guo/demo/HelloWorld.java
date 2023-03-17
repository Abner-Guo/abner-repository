package pers.guo.demo;

/**
 * Spring项目测试demo
 * @author: guochao.bj@fang.com
 * @createDate: 2023/3/17 09:53
 */
public class HelloWorld {

    private String name;

    public HelloWorld() {
    }

    public HelloWorld(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void sayHello() {

        System.out.println( "Hello World!" );

    }


}
