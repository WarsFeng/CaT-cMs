package cat.wars.cms.manager_course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 4/8/19
 * Time: 9:50 AM
 */

@SpringBootApplication
@EntityScan(basePackages = "cat.wars.cms.framework.domain")
@ComponentScan(basePackages = {
        "cat.wars.cms.manager_course"
        , "cat.wars.cms.api.course" // Swagger api
        , "cat.wars.cms.framework" // Exception...
        , "cat.wars.cms.api.config" // Exception...
})
@EnableFeignClients(basePackages = "cat.wars.cms.manager_course.client")
public class CmsManagerCourseApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmsManagerCourseApplication.class, args);
    }
}
