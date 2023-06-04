package pers.guo.repositorytemplate;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pers.guo.repositorytemplate.design.DimensionHandle;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

@SpringBootTest
class RepositoryTemplateApplicationTests {

    @Test
    void contextLoads() {
    }

    @Resource
    DimensionHandle dimensionHandle;

    @Test
    public void FctsttMainTest(){
        dimensionHandle.handleDimensionSet(new HashSet<>(),"city");
    }

}
