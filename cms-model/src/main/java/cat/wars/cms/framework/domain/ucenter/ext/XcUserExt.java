package cat.wars.cms.framework.domain.ucenter.ext;

import cat.wars.cms.framework.domain.ucenter.XcMenu;
import cat.wars.cms.framework.domain.ucenter.XcUser;
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
public class XcUserExt extends XcUser {

    //权限信息
    private List<XcMenu> permissions;

    //企业信息
    private String companyId;
}
