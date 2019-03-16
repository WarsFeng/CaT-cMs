package cat.wars.cms.manager.service.impl;

import cat.wars.cms.framework.domain.cms.CmsPage;
import cat.wars.cms.framework.domain.cms.request.CmsQueryPageRequest;
import cat.wars.cms.framework.model.response.QueryResponseResult;
import cat.wars.cms.manager.service.CmsPageService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CmsPageServiceImplTest {

    @Autowired
    private CmsPageService service;

    @Test
    public void findList() {
        int page = 1, size = 1;
        CmsQueryPageRequest request = new CmsQueryPageRequest();
        // Find one
        QueryResponseResult result = service.findList(page, size, request);
        Assert.assertTrue(result.isSuccess());
        CmsPage cmsPage = (CmsPage) result.getQueryResult().getList().get(0);

        BeanUtils.copyProperties(cmsPage, request);
        // Find two
        result = service.findList(page, size, request);
        boolean equals = cmsPage.equals(result.getQueryResult().getList().get(0));
        Assert.assertTrue(equals);
    }
}