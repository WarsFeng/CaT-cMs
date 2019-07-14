package cat.wars.cms.framework.domain.cms.response;

import cat.wars.cms.framework.model.response.ResponseResult;
import cat.wars.cms.framework.model.response.ResultCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CoursePreviewResult extends ResponseResult {

    String previewUrl;

    public CoursePreviewResult(ResultCode resultCode, String previewUrl) {
        super(resultCode);
        this.previewUrl = previewUrl;
    }
}
