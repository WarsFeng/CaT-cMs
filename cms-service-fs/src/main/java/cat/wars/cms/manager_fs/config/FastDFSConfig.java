package cat.wars.cms.manager_fs.config;

import cat.wars.cms.framework.domain.filesystem.response.FileSystemCode;
import cat.wars.cms.framework.exception.ExceptionCast;
import lombok.Setter;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.TrackerClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 6/3/19
 * Time: 12:24 PM
 * FastDFS configuration
 */

@Configuration
@ConfigurationProperties(prefix = "cms.fastdfs")
public class FastDFSConfig {

    @Setter
    private int connectTimeoutInSeconds;
    @Setter
    private int networkTimeoutInSeconds;
    @Setter
    private String charset;
    @Setter
    private String trackerServers;

    private void initFDFS() {
        try {
            ClientGlobal.initByTrackers(trackerServers);
            ClientGlobal.setG_connect_timeout(connectTimeoutInSeconds);
            ClientGlobal.setG_network_timeout(networkTimeoutInSeconds);
            ClientGlobal.setG_charset(charset);
        } catch (Exception e) {
            e.printStackTrace();
            ExceptionCast.cast(FileSystemCode.FS_INIT_ERROR);
        }
    }

    @Bean
    public TrackerClient getTrackerClient() {
        initFDFS();
        return new TrackerClient();
    }
}
