package cat.wars.cms.manager_fs.service.impl;

import cat.wars.cms.framework.domain.filesystem.FileSystemImage;
import cat.wars.cms.framework.domain.filesystem.response.FileSystemCode;
import cat.wars.cms.framework.domain.filesystem.response.UploadImageResult;
import cat.wars.cms.framework.exception.ExceptionCast;
import cat.wars.cms.framework.model.response.CommonCode;
import cat.wars.cms.manager_fs.dao.FileSystemImageRepository;
import cat.wars.cms.manager_fs.service.FileSystemImageService;
import com.alibaba.fastjson.JSON;
import org.csource.common.MyException;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 6/2/19
 * Time: 11:33 AM
 * FS image service basic implementation
 */

@Service
public class FileSystemImageServiceImpl implements FileSystemImageService {

    private final TrackerClient trackerClient;
    private final FileSystemImageRepository repository;

    public FileSystemImageServiceImpl(TrackerClient trackerClient, FileSystemImageRepository repository) {
        this.trackerClient = trackerClient;
        this.repository = repository;
    }

    @Override
    public UploadImageResult upload(MultipartFile multipartFile, String fileTag, String businessKey, String metadata) {
        // Store to fs
        String fileId = storeImage(multipartFile);
        if (isEmpty(fileId)) ExceptionCast.cast(FileSystemCode.FS_UPLOADFILE_SERVERFAIL);
        // Save
        FileSystemImage fSImage = new FileSystemImage();
        fSImage.setFileId(fileId); // File id
        fSImage.setFilePath(fileId); // File path
        fSImage.setFiletag(fileTag); // Caller tag
        fSImage.setBusinesskey(businessKey); // Caller business key
        fSImage.setFileName(multipartFile.getOriginalFilename()); // Origin name
        fSImage.setFileSize(multipartFile.getSize()); // File size
        fSImage.setFileType(multipartFile.getContentType()); // File type
        if (isNotEmpty(metadata)) { // Image metadata
            try {
                Map metadataMap = JSON.parseObject(metadata, Map.class);
                fSImage.setMetadata(metadataMap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        fSImage = repository.save(fSImage);
        // Saved, result
        return new UploadImageResult(CommonCode.SUCCESS, fSImage);
    }

    /**
     * Store image to FastDFS
     *
     * @param multipartFile {@link MultipartFile}
     * @return file id
     */
    private String storeImage(MultipartFile multipartFile) {
        try {
            // Get ext name
            byte[] fileBytes = multipartFile.getBytes();
            String originalFilename = multipartFile.getOriginalFilename();
            if (originalFilename == null || 0 == fileBytes.length)
                ExceptionCast.cast(FileSystemCode.FS_UPLOADFILE_FILEISNULL);
            String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

            // Connect server
            TrackerServer tracker = trackerClient.getConnection();
            StorageServer storage = trackerClient.getStoreStorage(tracker);
            StorageClient1 storageClient = new StorageClient1(tracker, storage);

            // Upload
            return storageClient.upload_file1(fileBytes, extName, null);

        } catch (IOException e) {
            e.printStackTrace();
            ExceptionCast.cast(FileSystemCode.FS_CONNECTION_ERROR);
        } catch (MyException e) {
            e.printStackTrace();
            ExceptionCast.cast(FileSystemCode.FS_UPLOADFILE_SERVERFAIL);
        }
        return null;
    }
}
