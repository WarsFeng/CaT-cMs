package cat.wars.cms.manager_course.client;

import cat.wars.cms.framework.domain.cms.CmsPage;
import cat.wars.cms.framework.domain.cms.response.CmsPageResult;
import cat.wars.cms.framework.domain.cms.response.CmsPostPageResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 7/8/19
 * Time: 12:59 AM
 * Cms page client from(CMS manager Service)
 */

@FeignClient("CMS-SERVICE-MANAGER")
public interface CmsPageClient {

    @PostMapping("/cms/page/save")
    CmsPageResult save(@RequestBody CmsPage page);

    @PostMapping("/cms/page/release_quick")
    CmsPostPageResult releaseQuick(@RequestBody CmsPage page);
}
