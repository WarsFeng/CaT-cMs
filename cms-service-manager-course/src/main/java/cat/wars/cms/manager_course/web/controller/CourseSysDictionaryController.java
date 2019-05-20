package cat.wars.cms.manager_course.web.controller;

import cat.wars.cms.api.course.CourseSysDictionaryControllerApi;
import cat.wars.cms.framework.domain.course.response.SysDictionaryResponse;
import cat.wars.cms.manager_course.service.CourseSysDictionaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 4/21/19
 * Time: 11:28 AM
 */

@Slf4j
@RestController
@RequestMapping("/course/sys/dictionary")
public class CourseSysDictionaryController implements CourseSysDictionaryControllerApi {

    private final CourseSysDictionaryService service;

    public CourseSysDictionaryController(CourseSysDictionaryService service) {
        this.service = service;
    }

    @Override
    @GetMapping("/get/{type}")
    public SysDictionaryResponse getByType(@PathVariable(name = "type") String type) {
        log.info("Query data dictionary by type, type({})", type);
        return service.getByType(type);
    }
}
