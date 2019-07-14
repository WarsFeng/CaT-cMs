package cat.wars.cms.eureka.center;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 6/6/19
 * Time: 2:39 AM
 * Application
 */

@EnableEurekaServer
@SpringBootApplication
public class CmsEurekaCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmsEurekaCenterApplication.class, args);
    }
}
