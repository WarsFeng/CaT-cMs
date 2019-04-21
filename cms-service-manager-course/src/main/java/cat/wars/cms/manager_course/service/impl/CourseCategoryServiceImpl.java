package cat.wars.cms.manager_course.service.impl;

import cat.wars.cms.framework.domain.course.ext.CategoryNode;
import cat.wars.cms.framework.model.response.CommonCode;
import cat.wars.cms.framework.model.response.QueryResponseResult;
import cat.wars.cms.framework.model.response.QueryResult;
import cat.wars.cms.manager_course.dao.CourseCategoryMapper;
import cat.wars.cms.manager_course.service.CourseCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 4/20/19
 * Time: 8:52 PM
 */

@Service
public class CourseCategoryServiceImpl implements CourseCategoryService {

    private final CourseCategoryMapper mapper;

    public CourseCategoryServiceImpl(CourseCategoryMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public QueryResponseResult<CategoryNode> findCategoryNodeList(String categoryName) {
        // Query
        if (isNotEmpty(categoryName)) categoryName = "%" + categoryName + "%";
        List<CategoryNode> nodeList = mapper.findNodeList(categoryName);

        // Result
        return new QueryResponseResult<>(CommonCode.SUCCESS, new QueryResult<>(nodeList, 0));
    }
}
