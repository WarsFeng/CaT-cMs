package cat.wars.cms.manager_fs.web.controller;

import cat.wars.cms.api.filesystem.FileSystemImageControllerApi;
import cat.wars.cms.framework.domain.filesystem.response.UploadImageResult;
import cat.wars.cms.manager_fs.service.FileSystemImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by IntelliJ IDEA.
 * User: wars
 * Date: 6/2/19
 * Time: 7:34 AM
 * FS image controller
 */

@Slf4j
@RestController
@RequestMapping("/fs/image")
public class FileSystemImageController implements FileSystemImageControllerApi {

    private final FileSystemImageService service;

    public FileSystemImageController(FileSystemImageService service) {
        this.service = service;
    }

    @Override
    @PostMapping("/upload")
    public UploadImageResult upload(
            @RequestParam("file") MultipartFile multipartFile
            , @RequestParam String fileTag
            , @RequestParam String businessKey
            , @RequestParam(required = false) String metadata
    ) {
        log.info("Upload image, fileTag({}), businessKey({}), metadata(\n\t{}\n)", fileTag, businessKey, metadata);
        return service.upload(multipartFile, fileTag, businessKey, metadata);
    }
}
