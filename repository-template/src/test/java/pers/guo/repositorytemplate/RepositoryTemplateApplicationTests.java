package pers.guo.repositorytemplate;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pers.guo.repositorytemplate.design.DimensionHandle;
import pers.guo.repositorytemplate.server.IdGeneratorService;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

@SpringBootTest
class RepositoryTemplateApplicationTests {

    @Resource
    IdGeneratorService idGeneratorService;

    @Test
    void contextLoads() {
    }

    @Resource
    DimensionHandle dimensionHandle;

    @Test
    public void FctsttMainTest(){
        dimensionHandle.handleDimensionSet(new HashSet<>(),"city");
    }

    @Test
    void generateIdTest() {
        String code = idGeneratorService.generateId("orderId", 6);
        System.out.println(code);
    }

}
