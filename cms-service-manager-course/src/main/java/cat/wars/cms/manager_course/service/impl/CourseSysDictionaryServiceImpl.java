package cat.wars.cms.manager_course.service.impl;

import cat.wars.cms.framework.domain.course.response.CourseCode;
import cat.wars.cms.framework.domain.course.response.SysDictionaryResponse;
import cat.wars.cms.framework.domain.system.SysDictionary;
import cat.wars.cms.framework.exception.ExceptionCast;
import cat.wars.cms.framework.model.response.CommonCode;
import cat.wars.cms.manager_course.dao.CourseSysDictionaryRepository;
import cat.wars.cms.manager_course.service.CourseSysDictionaryService;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 4/21/19
 * Time: 2:09 PM
 */

@Service
public class CourseSysDictionaryServiceImpl implements CourseSysDictionaryService {

    private final CourseSysDictionaryRepository repository;

    public CourseSysDictionaryServiceImpl(CourseSysDictionaryRepository repository) {
        this.repository = repository;
    }

    @Override
    public SysDictionaryResponse getByType(String type) {
        // Filter data
        if (isEmpty(type)) ExceptionCast.cast(CourseCode.COURSE_DICTIONARY_TYPE_ISNULL);
        Optional<SysDictionary> dictionaryOptional;
        if ((dictionaryOptional = repository.findByDType(type)).isEmpty())
            ExceptionCast.cast(CourseCode.COURSE_TEACH_PLAN_PARENT_NOT_EXISTS);
        // Result
        return new SysDictionaryResponse(CommonCode.SUCCESS, dictionaryOptional.get());
    }
}
