package cat.wars.cms.manager_course.web.controller;

import cat.wars.cms.api.course.CourseCategoryControllerApi;
import cat.wars.cms.framework.domain.course.ext.CategoryNode;
import cat.wars.cms.framework.model.response.QueryResponseResult;
import cat.wars.cms.manager_course.service.CourseCategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 4/20/19
 * Time: 7:44 PM
 * Course category controller
 */

@RestController
@RequestMapping("/course/category")
public class CourseCategoryController implements CourseCategoryControllerApi {

    private final CourseCategoryService service;

    public CourseCategoryController(CourseCategoryService service) {
        this.service = service;
    }

    @Override
    @GetMapping("/list")
    public QueryResponseResult<CategoryNode> findCategoryNodeList(String categoryName) {
        return service.findCategoryNodeList(categoryName);
    }
}
