package cat.wars.cms.manager.service.impl;

import cat.wars.cms.framework.domain.cms.CmsTemplate;
import cat.wars.cms.framework.domain.cms.response.CmsCode;
import cat.wars.cms.framework.exception.ExceptionCast;
import cat.wars.cms.framework.model.response.CommonCode;
import cat.wars.cms.framework.model.response.QueryResponseResult;
import cat.wars.cms.framework.model.response.QueryResult;
import cat.wars.cms.manager.dao.CmsSiteRepository;
import cat.wars.cms.manager.dao.CmsTemplateRepository;
import cat.wars.cms.manager.service.CmsTemplateService;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 3/25/19
 * Time: 11:08 PM
 * Cms template service basic implements
 */

@Service
public class CmsTemplateServiceImpl implements CmsTemplateService {

    private final CmsTemplateRepository repository;
    private final CmsSiteRepository siteRepository;
    private final GridFSBucket gridFSBucket;
    private final GridFsTemplate gridFsTemplate;

    @Autowired
    public CmsTemplateServiceImpl(CmsSiteRepository siteRepository, CmsTemplateRepository repository, GridFSBucket gridFSBucket, GridFsTemplate gridFsTemplate) {
        this.siteRepository = siteRepository;
        this.repository = repository;
        this.gridFSBucket = gridFSBucket;
        this.gridFsTemplate = gridFsTemplate;
    }

    @Override
    public QueryResponseResult findSubList(String siteId, String query) {
        // Limit 10
        PageRequest pageable = PageRequest.of(0, 10);
        // Site not exists
        if (isEmpty(siteId) || !siteRepository.existsById(siteId))
            ExceptionCast.cast(CmsCode.CMS_MANAGER_REQUEST_INVALID);
        // Query
        Page<CmsTemplate> pages;
        if (isEmpty(query)) // Basic limit query
            pages = repository.findAll(pageable);
        else { // Fuzzy query by templateName
            CmsTemplate probe = new CmsTemplate();
            probe.setTemplateName(query);

            ExampleMatcher matching = ExampleMatcher.matching().withMatcher("templateName", matcher -> matcher.contains());
            pages = repository.findAll(Example.of(probe, matching), pageable);
        }
        // Clean properties
        List<CmsTemplate> templateList = pages.getContent();
        templateList.forEach(cmsTemplate -> {
            cmsTemplate.setSiteId(null);
            cmsTemplate.setTemplateParameter(null);
            cmsTemplate.setTemplateFileId(null);
        });
        // Result
        QueryResult<CmsTemplate> queryResult = new QueryResult<>();
        queryResult.setTotal(pages.getTotalElements());
        queryResult.setList(templateList);
        return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
    }

    @Override
    public CmsTemplate getById(String id) {
        // Filter request data
        if (isEmpty(id)) ExceptionCast.cast(CmsCode.CMS_MANAGER_REQUEST_INVALID);

        Optional<CmsTemplate> templateOptional = repository.findById(id);
        if (templateOptional.isEmpty()) ExceptionCast.cast(CmsCode.CMS_MANAGER_PAGE_NOT_EXISTS);

        return templateOptional.get();
    }

    @Override
    public String getTemplateStr(String id) {
        // Get file
        String fileId = getById(id).getTemplateFileId();
        GridFSFile file = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(fileId)));
        if (null == file) ExceptionCast.cast(CmsCode.CMS_MANAGER_REQUEST_INVALID);
        // Get stream
        GridFSDownloadStream downloadStream = gridFSBucket.openDownloadStream(file.getObjectId());
        try {
            // Convert to string
            return IOUtils.toString(downloadStream, Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
