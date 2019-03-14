package cat.wars.cms.framework.domain.course.request;

import cat.wars.cms.framework.model.request.RequestData;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Created by mrt on 2018/4/13.
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class CourseListRequest extends RequestData {
    //公司Id
    private String companyId;
}
