package cat.wars.cms.manager_fs.dao;

import cat.wars.cms.framework.domain.filesystem.FileSystemImage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 6/2/19
 * Time: 11:35 AM
 * FS image dao for jpa
 */

@Repository
public interface FileSystemImageRepository extends MongoRepository<FileSystemImage, String> {
}
