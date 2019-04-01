package cat.wars.cms.manager.config;

import cat.wars.cms.framework.domain.cms.response.CmsConfigResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RestTemplateConfigTest {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void testRestTemplate() {
        ResponseEntity<CmsConfigResult> response = restTemplate.getForEntity("http://127.0.0.1:20000/cms/config/getmodel/5a791725dd573c3574ee333f", CmsConfigResult.class);
        System.out.println(response);
    }
}