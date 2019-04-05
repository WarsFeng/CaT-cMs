package cat.wars.cms.manager_client.config;

import com.mongodb.MongoClient;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 4/5/19
 * Time: 9:24 PM
 * MongoDB client configuration
 */

@Configuration
public class MongoConfig {

    @Value("${spring.data.mongodb.database}")
    private String dbName;

    @Bean
    public GridFSBucket gridFSBucket(MongoClient mongoClient) {
        return GridFSBuckets.create(mongoClient.getDatabase(dbName));
    }
}
