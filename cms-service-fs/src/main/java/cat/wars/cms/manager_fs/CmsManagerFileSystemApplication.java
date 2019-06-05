package cat.wars.cms.manager_fs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 5/29/19
 * Time: 9:52 PM
 */

@SpringBootApplication
@EntityScan(basePackages = "cat.wars.cms.framework.domain")
@ComponentScan(basePackages = {
        "cat.wars.cms.manager_fs"
        , "cat.wars.cms.api.filesystem" // Swagger api
        , "cat.wars.cms.framework" // Exception...
        , "cat.wars.cms.api.config" // Exception...
})
public class CmsManagerFileSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmsManagerFileSystemApplication.class, args);
    }
}
