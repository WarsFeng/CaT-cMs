package cat.wars.cms.framework.domain.media.response;

import cat.wars.cms.framework.domain.media.MediaFile;
import cat.wars.cms.framework.model.response.ResponseResult;
import cat.wars.cms.framework.model.response.ResultCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by mrt on 2018/3/31.
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MediaFileResult extends ResponseResult {
    MediaFile mediaFile;

    public MediaFileResult(ResultCode resultCode, MediaFile mediaFile) {
        super(resultCode);
        this.mediaFile = mediaFile;
    }
}
