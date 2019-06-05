package cat.wars.cms.manager_fs.service;

import cat.wars.cms.framework.domain.filesystem.response.UploadImageResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 6/2/19
 * Time: 7:43 AM
 * FS image service interface
 */

public interface FileSystemImageService {

    /**
     * Upload image
     *
     * @param multipartFile image
     * @param fileTag       caller tag
     * @param businessKey   caller business key
     * @param metadata      image metadata
     * @return cat.wars.cms.framework.domain.filesystem.response.UploadImageResult
     */
    UploadImageResult upload(MultipartFile multipartFile, String fileTag, String businessKey, String metadata);
}
