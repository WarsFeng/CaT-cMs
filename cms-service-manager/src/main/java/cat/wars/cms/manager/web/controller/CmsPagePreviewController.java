package cat.wars.cms.manager.web.controller;

import cat.wars.cms.framework.web.BaseController;
import cat.wars.cms.manager.service.CmsPageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 4/1/19
 * Time: 4:31 PM
 * Cms page preview controller
 */

@Slf4j
@Controller
public class CmsPagePreviewController extends BaseController {

    private final CmsPageService service;

    public CmsPagePreviewController(CmsPageService service) {
        this.service = service;
    }

    @GetMapping("/cms/page/preview/{id}")
    public void preview(@PathVariable(name = "id") String id) {
        log.info("\nCms page preview, id({})", id);
        try {
            String pageHtml = service.getPageHtml(id);
            response.getOutputStream().write(pageHtml.getBytes(Charset.forName("UTF-8")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
