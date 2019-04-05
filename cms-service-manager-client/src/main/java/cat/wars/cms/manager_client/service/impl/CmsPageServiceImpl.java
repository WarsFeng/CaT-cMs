package cat.wars.cms.manager_client.service.impl;

import cat.wars.cms.framework.domain.cms.CmsPage;
import cat.wars.cms.manager_client.dao.CmsPageRepository;
import cat.wars.cms.manager_client.dao.CmsSiteRepository;
import cat.wars.cms.manager_client.service.CmsPageService;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import org.apache.commons.io.IOUtils;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 4/5/19
 * Time: 6:53 PM
 * Cms page service basic implementation
 */

@Service
public class CmsPageServiceImpl implements CmsPageService {


    private final CmsPageRepository repository;
    private final CmsSiteRepository cmsSiteRepository;
    private final GridFsTemplate gridFsTemplate;
    private final GridFSBucket gridFSBucket;

    public CmsPageServiceImpl(CmsPageRepository repository, CmsSiteRepository cmsSiteRepository, GridFsTemplate gridFsTemplate, GridFSBucket gridFSBucket) {
        this.repository = repository;
        this.cmsSiteRepository = cmsSiteRepository;
        this.gridFsTemplate = gridFsTemplate;
        this.gridFSBucket = gridFSBucket;
    }

    @Override
    public boolean savePageToServerPath(String id) {
        // Get page file
        CmsPage page = repository.findById(id).get();
        ObjectId fileObjectId = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(page.getHtmlFileId()))).getObjectId();
        GridFSDownloadStream downloadStream = gridFSBucket.openDownloadStream(fileObjectId);

        // Save to server path
        /// Full path = sitePath + pagePath + pageName
        String path = cmsSiteRepository.findById(page.getSiteId()).get().getSitePhysicalPath()
                + page.getPagePhysicalPath() + page.getPageName();
        /// Save
        try {
            IOUtils.copy(downloadStream, new FileOutputStream(path));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
