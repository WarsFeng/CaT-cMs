package cat.wars.cms.manager_course.web.controller;

import cat.wars.cms.api.course.CourseMarketControllerApi;
import cat.wars.cms.framework.domain.course.CourseMarket;
import cat.wars.cms.framework.domain.course.response.CourseMarketResponse;
import cat.wars.cms.framework.model.response.ResponseResult;
import cat.wars.cms.manager_course.service.CourseMarketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 5/20/19
 * Time: 11:39 AM
 */

@Slf4j
@RestController
@RequestMapping("/course/market")
public class CourseMarketController implements CourseMarketControllerApi {

    private final CourseMarketService service;

    public CourseMarketController(CourseMarketService service) {
        this.service = service;
    }

    @Override
    @GetMapping("/get/{id}")
    public CourseMarketResponse getById(@PathVariable(name = "id") String id) {
        log.info("\nQuery market by id, id({})", id);
        return service.findById(id);
    }

    @Override
    @PutMapping("/save/{id}")
    public ResponseResult save(@PathVariable(name = "id") String id, @RequestBody CourseMarket market) {
        log.info("\nSave market, market(\n\t{}\n)", market);
        return service.save(id, market);
    }
}
