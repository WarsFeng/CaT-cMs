package cat.wars.cms.framework.domain.cms.ext;

import cat.wars.cms.framework.domain.cms.CmsPage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class CmsPageExt extends CmsPage {
    private String htmlValue;

}
