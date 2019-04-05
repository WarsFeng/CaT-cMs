package cat.wars.cms.manager_client.service;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 4/5/19
 * Time: 6:09 PM
 * Cms page service interface
 */

public interface CmsPageService {

    /**
     * Save page to server path by id
     * the page has been verified on the server
     *
     * @param id page id
     * @return boolean of success
     */
    boolean savePageToServerPath(String id);
}
