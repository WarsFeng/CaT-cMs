package cat.wars.cms.framework.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @Author: mrt.
 * @Description:
 * @Date:Created in 2018/1/24 18:33.
 * @Modified By:
 */
@Data
@ToString
@NoArgsConstructor
public class QueryResult<T> {

    public QueryResult(List<T> list, long total) {
        this.list = list;
        this.total = total;
    }

    //数据列表
    private List<T> list;
    //数据总数
    private long total;
}
