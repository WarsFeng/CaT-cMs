package cat.wars.cms.framework.domain.cms.ext;

import cat.wars.cms.framework.domain.cms.CmsTemplate;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class CmsTemplateExt extends CmsTemplate {

    //模版内容
    private String templateValue;

}
