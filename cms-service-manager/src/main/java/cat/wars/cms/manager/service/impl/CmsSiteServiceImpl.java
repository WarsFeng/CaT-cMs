package cat.wars.cms.manager.service.impl;

import cat.wars.cms.framework.domain.cms.CmsSite;
import cat.wars.cms.framework.model.response.CommonCode;
import cat.wars.cms.framework.model.response.QueryResponseResult;
import cat.wars.cms.framework.model.response.QueryResult;
import cat.wars.cms.manager.dao.CmsSiteRepository;
import cat.wars.cms.manager.service.CmsSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 3/24/19
 * Time: 10:43 PM
 * Cms site service basic implements
 */

@Service
public class CmsSiteServiceImpl implements CmsSiteService {

    private final CmsSiteRepository repository;

    @Autowired
    public CmsSiteServiceImpl(CmsSiteRepository repository) {
        this.repository = repository;
    }

    @Override
    public QueryResponseResult findSubList(String siteName) {
        // Limit 10 and sort
        PageRequest pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "siteCreateTime");
        Page<CmsSite> pages;
        // Basic query
        if (isEmpty(siteName))
            pages = repository.findAll(pageable);
        else {

            // Fuzzy query by siteName(contains)
            CmsSite probe = new CmsSite();
            probe.setSiteName(siteName);

            ExampleMatcher matching = ExampleMatcher.matching().withMatcher("siteName", matcher -> matcher.contains());
            Example<CmsSite> example = Example.of(probe, matching);
            pages = repository.findAll(example, pageable);
        }
        // Result
        QueryResult<CmsSite> queryResult = new QueryResult<>();
        queryResult.setTotal(pages.getTotalElements());
        List<CmsSite> siteList = pages.getContent();
        // Clean properties
        siteList.forEach(cmsSite -> {
            cmsSite.setSiteDomain(null);
            cmsSite.setSitePort(null);
            cmsSite.setSiteWebPath(null);
            cmsSite.setSiteCreateTime(null);
        });
        queryResult.setList(siteList);
        return new QueryResponseResult(CommonCode.SUCCESS, queryResult);
    }
}
