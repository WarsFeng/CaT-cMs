package cat.wars.cms.manager_course.dao;

import cat.wars.cms.framework.domain.course.ext.CategoryNode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 4/20/19
 * Time: 8:53 PM
 * Course category dao by mybatis
 */

@Mapper
public interface CourseCategoryMapper {

    /**
     * Like query category node list
     *
     * @param categoryName category name
     * @return java.util.List<cat.wars.cms.framework.domain.course.ext.CategoryNode>
     */
    List<CategoryNode> findNodeList(String categoryName);
}
