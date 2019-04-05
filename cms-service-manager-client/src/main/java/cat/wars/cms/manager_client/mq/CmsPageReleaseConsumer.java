package cat.wars.cms.manager_client.mq;

import cat.wars.cms.manager_client.service.CmsPageService;
import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 4/5/19
 * Time: 11:22 PM
 * Cms page release consumer
 */

@Component
public class CmsPageReleaseConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(CmsPageReleaseConsumer.class);

    private final CmsPageService cmsPageService;

    public CmsPageReleaseConsumer(CmsPageService cmsPageService) {
        this.cmsPageService = cmsPageService;
    }

    /**
     * Cms page release, message format: {pageId : ""}
     * if fails, channel not ack
     *
     * @param message {@link Message}
     * @param channel {@link Channel}
     */
    @RabbitListener(queues = {"${spring.rabbitmq.param.queue}"})
    public void pageRelease(Message message, Channel channel /*, @Header(AmqpHeaders.DELIVERY_TAG) long tag*/) {
        String msg = new String(message.getBody(), Charset.forName("UTF-8"));
        LOGGER.info("\nReceive page release message, msg: ({})", msg);
        Map msgMap = JSON.parseObject(msg, Map.class);
        try {
            // If success, ack(multiple)
            if (cmsPageService.savePageToServerPath((String) msgMap.get("pageId"))) {
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
            }
        } catch (IOException e) {
            LOGGER.error("\nCms client page release consumer ack error", e);
        }
    }
}
