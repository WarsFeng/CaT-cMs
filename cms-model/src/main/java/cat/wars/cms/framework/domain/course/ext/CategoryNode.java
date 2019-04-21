package cat.wars.cms.framework.domain.course.ext;

import cat.wars.cms.framework.domain.course.Category;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * Created by admin on 2018/2/7.
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class CategoryNode extends Category {

    List<CategoryNode> children;
}
