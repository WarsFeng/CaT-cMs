package cat.wars.cms.manager_client.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 4/5/19
 * Time: 10:19 AM
 * RabbitMQ configuration
 */

@Configuration
public class RabbitmqConfig {

    private static final String QUEUE_CMS_PAGE_RELEASE = "queue_cms_page_release";
    private static final String EXCHANGE_TOPIC_CMS_PAGE_RELEASE = "exchange_topic_cms_page_release";

    @Value("${spring.rabbitmq.params.queue}")
    private String queue_cms_page_release_name;
    @Value("${spring.rabbitmq.params.exchange.page-release}")
    private String exchange_routing_cms_page_release;
    @Value("${spring.rabbitmq.params.routingkey}")
    private String routing_key;


    @Bean(EXCHANGE_TOPIC_CMS_PAGE_RELEASE)
    public Exchange EXCHANGE_TOPIC_CMS_PAGE_RELEASE() {
        return ExchangeBuilder.topicExchange(exchange_routing_cms_page_release)
                .durable(true).build(); // Durable
    }

    @Bean(QUEUE_CMS_PAGE_RELEASE)
    public Queue QUEUE_CMS_PAGE_RELEASE() {
        return new Queue(queue_cms_page_release_name); // Default durable, not exclusive, not autoDelete ...
    }

    @Bean
    public Binding BINDING_QUEUE_CMS_PAGE_RELEASE(@Qualifier(EXCHANGE_TOPIC_CMS_PAGE_RELEASE) Exchange exchange, @Qualifier(QUEUE_CMS_PAGE_RELEASE) Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with(routing_key).noargs();
    }
}
