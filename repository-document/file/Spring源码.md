# sprint源码编译

##  环境地址

- 开源地址：https://github.com/spring-projects/spring-framework
- JDK：1.8

## 源码下载

- 先把开源项目Fork到自己的仓库，方便提交自己修改

![截屏2023-03-17 18.33.47](./img/Spring源码/截屏2023-03-17 18.33.47.png)

- IDEA 检出对应版本，对应 的小版本要检出tag,以`v5.3.24`例

  Git tag 相关命令

  >- 查看所有tag: `git tag -l`
  >- 检出tag: `git checkout ${tagName}`
  >- tag 很多，可以随时按`q`退出
  >- 查看tag 信息：`git show`

  ![截屏2023-03-17 18.44.45](./img/Spring源码/截屏2023-03-17 18.44.45.png)

## gradle 修改

- 远程地址修改

  - `build.gradle`文件修改

    ```gradle
    maven{ url "https://maven.aliyun.com/nexus/content/groups/public/" }
    maven { url "https://maven.aliyun.com/nexus/content/repositories/jcenter" }
    maven { url "https://repo.springsource.org/plugins-release" }
    ```

    ![截屏2023-03-17 19.00.17](./img/Spring源码/截屏2023-03-17 19.00.17.png)

  - `settings.gradle`文件修改

    ![截屏2023-03-17 19.01.04](./img/Spring源码/截屏2023-03-17 19.01.04-9051070.png)

    ```gradle
    maven { url "https://maven.aliyun.com/nexus/content/groups/public/" }
    maven { url "https://maven.aliyun.com/repository/public" }
    ```

​				

- IDEA相关设置

![截屏2023-03-17 19.05.31](./img/Spring源码/截屏2023-03-17 19.05.31.png)



## 编译

![截屏2023-03-17 19.09.27](./img/Spring源码/截屏2023-03-17 19.09.27.png)

编译完成

# 应用本地spring源码

<font color=red>注意：项目中Spring版本要和源码检出tag一致</font>

按图顺序操作

![截屏2023-03-17 13.27.07](./img/Spring源码/截屏2023-03-17 13.27.07.png)

![截屏2023-03-17 13.30.55](./img/Spring源码/截屏2023-03-17 13.30.55.png)

![截屏2023-03-17 13.28.08](./img/Spring源码/截屏2023-03-17 13.28.08.png)

![截屏2023-03-17 13.29.02](./img/Spring源码/截屏2023-03-17 13.29.02.png)

![截屏2023-03-17 13.29.05](./img/Spring源码/截屏2023-03-17 13.29.05.png)



# 报错记录

1. kotlin&kotlinx相关依赖报错

https://blog.csdn.net/qq_39530821/article/details/120540134?spm=1001.2014.3001.5506

2. 使用版本对用的gradle版本编译

   源码中找到gradle-wrapper.properties文件,打开此文件看到如下内容：

   ```
   distributionBase=GRADLE_USER_HOME
   distributionPath=wrapper/dists
   distributionUrl=https\://services.gradle.org/distributions/gradle-7.5.1-bin.zip
   zipStoreBase=GRADLE_USER_HOME
   zipStorePath=wrapper/dists
   ```

   IDEA 也配置`gradle-7.5.1`







# 参考地址

https://blog.csdn.net/niuqingyi/article/details/114272794

https://blog.csdn.net/aqin1012/article/details/124295132


https://www.cnblogs.com/GTbk/p/17007328.html


https://blog.csdn.net/diwanglingS/article/details/105832482

https://blog.csdn.net/qq_41646249/article/details/119777084

https://blog.csdn.net/xyaicwj/article/details/125256149



```shell
git remote set-url origin https://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@github.com/Abner-Guo/spring-framework.git
```



