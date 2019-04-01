package cat.wars.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 3/14/19
 * Time: 2:20 PM
 * Cms manager main
 */

@SpringBootApplication
@EntityScan("cat.wars.cms.framework.domain.cms")
@ComponentScans({
        @ComponentScan(basePackages = {"cat.wars.cms.manager"}),
        @ComponentScan(basePackages = {"cat.wars.cms.api.cms"}), // Swagger api
        @ComponentScan(basePackages = {"cat.wars.cms.framework"})// Exception...
})
public class CmsManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmsManagerApplication.class, args);
    }
}
