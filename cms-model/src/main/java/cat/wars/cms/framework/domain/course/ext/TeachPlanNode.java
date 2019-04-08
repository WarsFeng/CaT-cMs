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
public class TeachPlanNode extends TeachPlan {

    List<TeachPlanNode> children;
}
