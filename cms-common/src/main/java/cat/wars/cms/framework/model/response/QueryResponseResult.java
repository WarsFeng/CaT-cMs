package cat.wars.cms.framework.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class QueryResponseResult<T> extends ResponseResult {

    // T - list type
    QueryResult<T> queryResult;

    public QueryResponseResult(ResultCode resultCode, QueryResult<T> queryResult) {
        super(resultCode);
        this.queryResult = queryResult;
    }

}
