package cat.wars.cms.manager_course.service.impl;

import cat.wars.cms.framework.domain.course.CourseBase;
import cat.wars.cms.framework.domain.course.ext.CourseInfo;
import cat.wars.cms.framework.domain.course.request.CourseListRequest;
import cat.wars.cms.framework.domain.course.response.CourseCode;
import cat.wars.cms.framework.exception.ExceptionCast;
import cat.wars.cms.framework.model.response.CommonCode;
import cat.wars.cms.framework.model.response.QueryResponseResult;
import cat.wars.cms.framework.model.response.QueryResult;
import cat.wars.cms.framework.model.response.ResponseResult;
import cat.wars.cms.manager_course.dao.CourseMapper;
import cat.wars.cms.manager_course.dao.CourseRepository;
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
    private final CourseRepository repository;

    public CourseServiceImpl(CourseMapper mapper, CourseRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
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

    @Override
    public ResponseResult add(CourseBase course) {
        // Filter data
        if (isEmpty(course.getName()) || isEmpty(course.getGrade()) || isEmpty(course.getStudymodel()))
            ExceptionCast.cast(CourseCode.COURSE_ADD_INPUT_FIL);

        // Save
        course.setId(null);
        course.setStatus("202001"); // Unpublished
        repository.save(course);

        // Result
        return ResponseResult.SUCCESS();
    }
}
