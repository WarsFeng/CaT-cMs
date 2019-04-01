package cat.wars.cms.manager.config;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.apache.commons.io.IOUtils;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoConfigTest {

    @Autowired
    private GridFSBucket gridFSBucket;
    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Test
    public void testGridFSStore() throws IOException {
        ObjectId objectId = gridFsTemplate.store(new FileInputStream("/tmp/index_banner.html"), "轮播图测试", "");
        System.out.println(objectId);
    }

    @Test
    public void testGridFSBucket() throws IOException {
        String fileid = "5ca1cfcd9b89c6439dfd19bf";
        GridFSFile file = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(fileid)));
        GridFSDownloadStream downloadStream = gridFSBucket.openDownloadStream(file.getObjectId());
        System.out.println(IOUtils.toString(downloadStream, Charset.forName("UTF-8")));
    }
}