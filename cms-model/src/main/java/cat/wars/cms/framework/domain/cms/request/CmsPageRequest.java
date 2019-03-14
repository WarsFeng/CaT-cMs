package cat.wars.cms.framework.domain.cms.request;

import cat.wars.cms.framework.model.request.RequestData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 3/14/19
 * Time: 11:20 AM
 * Cms page request
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class CmsPageRequest extends RequestData {

    @ApiModelProperty("Page id")
    private String pageId;

    @ApiModelProperty("Site id")
    private String siteId;

    @ApiModelProperty("Template id")
    private String templateId;

    @ApiModelProperty("Page name")
    private String pageName;

    @ApiModelProperty("Page alias")
    private String pageAlias;
}
