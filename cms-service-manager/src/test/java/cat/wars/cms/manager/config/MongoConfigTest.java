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
import java.io.FileOutputStream;
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
        ObjectId objectId = gridFsTemplate.store(new FileInputStream("/tmp/course.ftl"), "Course detail test", "");
        System.out.println(objectId);
    }

    @Test
    public void testGridFSBucket() throws IOException {
        String fileid = "5d1533f09dcfd97eae6ea24a";
        GridFSFile file = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(fileid)));
        GridFSDownloadStream downloadStream = gridFSBucket.openDownloadStream(file.getObjectId());
        IOUtils.copy(downloadStream, new FileOutputStream("/tmp/course.ftl"));
        System.out.println(IOUtils.toString(downloadStream, Charset.forName("UTF-8")));
    }
}
