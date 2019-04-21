package cat.wars.cms.manager_course.dao;

import cat.wars.cms.framework.domain.course.ext.CategoryNode;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseCategoryMapperTest {

    @Autowired
    private CourseCategoryMapper mapper;

    @Test
    public void testFindNodeList() {
        List<CategoryNode> nodeList = mapper.findNodeList("%密码%");
        Assert.assertNotNull(nodeList);
    }
}