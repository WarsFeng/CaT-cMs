package cat.wars.cms.manager.config;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 4/6/19
 * Time: 1:35 AM
 * RabbitMQ Configuration
 */

@Configuration
public class RabbitmqConfig {

    private static final String EXCHANGE_TOPIC_CMS_PAGE_RELEASE = "exchange_topic_cms_page_release";

    @Value("${spring.rabbitmq.params.exchange.page-release}")
    private String EXCHANGE_ROUTING_CMS_PAGE_RELEASE;

    @Bean(EXCHANGE_TOPIC_CMS_PAGE_RELEASE)
    public Exchange EXCHANGE_TOPIC_CMS_PAGE_RELEASE() {
        return ExchangeBuilder.topicExchange(EXCHANGE_ROUTING_CMS_PAGE_RELEASE).build();
    }
}
