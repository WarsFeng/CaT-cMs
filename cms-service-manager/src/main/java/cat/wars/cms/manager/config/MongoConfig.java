package cat.wars.cms.manager.config;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 4/1/19
 * Time: 7:40 AM
 * Mongodb client config
 */

@Configuration
public class MongoConfig {

    @Value("${spring.data.mongodb.database}")
    private String dbName;

    @Bean
    public GridFSBucket gridFSBucket(MongoClient mongoClient) {
        MongoDatabase database = mongoClient.getDatabase(dbName);
        return GridFSBuckets.create(database);
    }
}
