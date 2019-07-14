package cat.wars.cms.manager_course.service.impl;

import cat.wars.cms.framework.domain.cms.CmsPage;
import cat.wars.cms.framework.domain.cms.response.CmsPageResult;
import cat.wars.cms.framework.domain.cms.response.CmsPostPageResult;
import cat.wars.cms.framework.domain.cms.response.CoursePreviewResult;
import cat.wars.cms.framework.domain.course.CourseBase;
import cat.wars.cms.framework.domain.course.CourseMarket;
import cat.wars.cms.framework.domain.course.CoursePic;
import cat.wars.cms.framework.domain.course.ext.CourseInfo;
import cat.wars.cms.framework.domain.course.ext.CourseView;
import cat.wars.cms.framework.domain.course.request.CourseListRequest;
import cat.wars.cms.framework.domain.course.response.CourseCode;
import cat.wars.cms.framework.domain.course.response.CourseResponse;
import cat.wars.cms.framework.domain.course.response.CourseViewResult;
import cat.wars.cms.framework.exception.ExceptionCast;
import cat.wars.cms.framework.model.response.CommonCode;
import cat.wars.cms.framework.model.response.QueryResponseResult;
import cat.wars.cms.framework.model.response.QueryResult;
import cat.wars.cms.framework.model.response.ResponseResult;
import cat.wars.cms.manager_course.client.CmsPageClient;
import cat.wars.cms.manager_course.dao.*;
import cat.wars.cms.manager_course.service.CourseService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 4/18/19
 * Time: 12:41 AM
 */

@Service
public class CourseServiceImpl implements CourseService {

    @Value("${course.publish.site-id}")
    private String siteId;
    @Value("${course.publish.template-id}")
    private String templateId;
    @Value("${course.publish.preview-url-pre}")
    private String previewUrlPre;
    @Value("${course.publish.page-web-path}")
    private String pageWebPath;
    @Value("${course.publish.page-physical-path}")
    private String pagePhysicalPath;
    @Value("${course.publish.data-url-pre}")
    private String dataUrlPre;

    private final CmsPageClient cmsPageClient;

    private final CourseMapper mapper;
    private final CourseRepository repository;
    private final CourseMarketRepository marketRepository;
    private final CourseTeachPlanMapper teachPlanMapper;
    private final CourseImageRepository imageRepository;


    public CourseServiceImpl(CmsPageClient cmsPageClient, CourseMapper mapper, CourseRepository repository, CourseMarketRepository marketRepository, CourseTeachPlanMapper teachPlanMapper, CourseImageRepository imageRepository) {
        // Remote
        this.cmsPageClient = cmsPageClient;
        // Local
        this.mapper = mapper;
        this.repository = repository;
        this.marketRepository = marketRepository;
        this.teachPlanMapper = teachPlanMapper;
        this.imageRepository = imageRepository;
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
    @Transactional
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

    @Override
    public CourseBase getById(String id) {
        Optional<CourseBase> courseOptional = null;
        if (isEmpty(id) || (courseOptional = repository.findById(id)).isEmpty())
            ExceptionCast.cast(CourseCode.COURSE_NOT_EXISTS);

        return courseOptional.get();
    }

    @Override
    public CourseResponse findById(String id) {
        return new CourseResponse(CommonCode.SUCCESS, getById(id));
    }

    @Override
    @Transactional
    public ResponseResult edit(CourseBase course) {
        // Filter data
        if (isEmpty(course.getName()) || isEmpty(course.getGrade()) || isEmpty(course.getStudymodel()))
            ExceptionCast.cast(CourseCode.COURSE_ADD_INPUT_FIL);
        // TODO User identity check

        // Update
        CourseBase dCourse = getById(course.getId());
        dCourse.setName(course.getName());
        dCourse.setUsers(course.getUsers());
        dCourse.setMt(course.getMt());
        dCourse.setSt(course.getSt());
        dCourse.setGrade(course.getGrade());
        dCourse.setStudymodel(course.getStudymodel());
        dCourse.setDescription(course.getDescription());
        // Save
        repository.save(course);

        // Result
        return ResponseResult.SUCCESS();
    }

    @Override
    public CourseViewResult view(String id) {
        CourseView courseView = new CourseView();
        // Base
        Optional<CourseBase> courseOptional;
        if ((courseOptional = repository.findById(id)).isPresent()) {
            courseView.setBase(courseOptional.get());
        }
        // Market
        Optional<CourseMarket> marketOptional;
        if ((marketOptional = marketRepository.findById(id)).isPresent()) {
            courseView.setMarket(marketOptional.get());
        }
        // TeachPlan
        courseView.setTeachPlan(teachPlanMapper.findNodeList(id));
        // Picture
        Optional<CoursePic> picOptional;
        if ((picOptional = imageRepository.findById(id)).isPresent()) {
            courseView.setPic(picOptional.get());
        }
        return new CourseViewResult(CommonCode.SUCCESS, courseView);
    }

    @Override
    public CoursePreviewResult preview(String id) {
        // Verify
        CmsPage page = this.pagePack(id);
        // Save to cms manager
        CmsPageResult saveResult = cmsPageClient.save(page);
        if (!saveResult.isSuccess())
            ExceptionCast.cast(CommonCode.FAIL);
        // Result
        return new CoursePreviewResult(CommonCode.SUCCESS, previewUrlPre + saveResult.getCmsPage().getPageId());
    }

    @Override
    @Transactional
    public CmsPostPageResult release(String id) {

        // Release page
        CmsPostPageResult result = this.releasePage(id);

        // Release course state
        this.releaseState(id);

        return result;
    }

    @Override
    public CmsPostPageResult releasePage(String id) {
        // Verify
        CmsPage page = this.pagePack(id);

        CmsPostPageResult postPageResult = cmsPageClient.releaseQuick(page);
        if (!postPageResult.isSuccess())
            ExceptionCast.cast(CommonCode.FAIL);
        return postPageResult;
    }

    @Override
    public void releaseState(String id) {
        CourseBase course = this.getById(id);
        // 202001-制作中 202002-已发布 202003-已下线
        course.setStatus("202002");
        if (!"202002".equals(repository.save(course).getStatus()))
            ExceptionCast.cast(CommonCode.FAIL);
    }

    /**
     * Pack template page from course id
     *
     * @param courseId course id
     * @return cat.wars.cms.framework.domain.cms.CmsPage
     */
    private CmsPage pagePack(String courseId) {
        CourseBase course = this.getById(courseId);

        CmsPage page = new CmsPage();
        page.setSiteId(siteId); // Site id
        page.setTemplateId(templateId); // Template id
        page.setPageName(courseId + ".html"); // Page name
        page.setPageAlias(course.getName()); // Course name
        page.setPageType("1"); // Page type

        page.setPagePhysicalPath(pagePhysicalPath); // Page save path
        page.setPageWebPath(pageWebPath); // Page web path
        page.setDataUrl(dataUrlPre + courseId); // Course data url
        return page;
    }
}
