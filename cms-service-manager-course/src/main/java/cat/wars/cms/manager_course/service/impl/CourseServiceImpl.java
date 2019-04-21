package cat.wars.cms.manager_course.service.impl;

import cat.wars.cms.framework.domain.course.ext.CourseInfo;
import cat.wars.cms.framework.domain.course.request.CourseListRequest;
import cat.wars.cms.framework.model.response.CommonCode;
import cat.wars.cms.framework.model.response.QueryResponseResult;
import cat.wars.cms.framework.model.response.QueryResult;
import cat.wars.cms.manager_course.dao.CourseMapper;
import cat.wars.cms.manager_course.service.CourseService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 4/18/19
 * Time: 12:41 AM
 */

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseMapper mapper;

    public CourseServiceImpl(CourseMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public QueryResponseResult<CourseInfo> findList(int page, int size, CourseListRequest params) {
        if (isEmpty(params.getCompanyId())) params.setCompanyId("");
        // Page and size process
        if (page <= 0) page = 1;
        if (size <= 0) size = 20;
        // Abstract page to real page
        //--page;

        // Query page
        PageHelper.startPage(page, size);
        Page<CourseInfo> pages = mapper.findCoursePage(params);

        // Result
        QueryResult<CourseInfo> queryResult = new QueryResult<>(pages.getResult(), pages.getTotal());
        return new QueryResponseResult<>(CommonCode.SUCCESS, queryResult);
    }
}
