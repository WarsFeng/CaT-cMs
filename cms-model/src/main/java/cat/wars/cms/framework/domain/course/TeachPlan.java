package cat.wars.cms.framework.domain.course;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by admin on 2018/2/7.
 */
@Data
@ToString
@Entity
@Table(name = "teach_plan")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class TeachPlan implements Serializable {

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32)
    private String id;
    private String pname;       // 计划名称
    private String parentid;    // 父级Id
    private String grade;       // 层级，分为1、2、3级
    private String ptype;       // 课程类型:1视频、2文档
    private String description; // 章节及课程时介绍
    private Double timelength;  // 时长，单位分钟
    private String courseid;    // 课程id
    private String status;      // 状态：未发布(0)、已发布(1)
    private Integer orderby;    // 排序字段
    private String trylearn;    // 是否试学
}
