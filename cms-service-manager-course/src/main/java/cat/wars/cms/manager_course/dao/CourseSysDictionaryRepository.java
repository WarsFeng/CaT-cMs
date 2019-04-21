package cat.wars.cms.manager_course.dao;

import cat.wars.cms.framework.domain.system.SysDictionary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 4/21/19
 * Time: 2:11 PM
 * Course sys dictionary dao by mongojpa
 */

@Repository
public interface CourseSysDictionaryRepository extends MongoRepository<SysDictionary, String> {

    Optional<SysDictionary> findByDType(String DType);
}
