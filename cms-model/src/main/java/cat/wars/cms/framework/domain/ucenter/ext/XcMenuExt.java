package cat.wars.cms.framework.domain.ucenter.ext;

import cat.wars.cms.framework.domain.course.ext.CategoryNode;
import cat.wars.cms.framework.domain.ucenter.XcMenu;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * Created by admin on 2018/3/20.
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class XcMenuExt extends XcMenu {

    List<CategoryNode> children;
}
