package cat.wars.cms.manager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 4/1/19
 * Time: 5:56 AM
 * RestTemplate config
 */

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(okHttp3ClientHttpRequestFactory());
    }

    @Bean
    public OkHttp3ClientHttpRequestFactory okHttp3ClientHttpRequestFactory() {
        OkHttp3ClientHttpRequestFactory requestFactory = new OkHttp3ClientHttpRequestFactory();
        requestFactory.setConnectTimeout(10000);
        requestFactory.setReadTimeout(10000);
        requestFactory.setWriteTimeout(10000);
        return requestFactory;
    }
}
