package cat.wars.cms.manager_client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 4/5/19
 * Time: 10:04 AM
 * Spring boot application
 */

@EntityScan(basePackages = "cat.wars.cms.framework.domain.cms")
@SpringBootApplication
public class CmsManagerClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmsManagerClientApplication.class, args);
    }
}
