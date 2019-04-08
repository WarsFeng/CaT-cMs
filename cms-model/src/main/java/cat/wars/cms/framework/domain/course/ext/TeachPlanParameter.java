package cat.wars.cms.framework.domain.course.ext;

import cat.wars.cms.framework.domain.course.TeachPlan;
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
public class TeachPlanParameter extends TeachPlan {

    //二级分类ids
    List<String> bIds;
    //三级分类ids
    List<String> cIds;

}
