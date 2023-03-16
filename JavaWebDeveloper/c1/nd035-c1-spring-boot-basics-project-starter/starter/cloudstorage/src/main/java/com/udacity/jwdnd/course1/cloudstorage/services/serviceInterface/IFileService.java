package com.udacity.jwdnd.course1.cloudstorage.services.serviceInterface;

import com.udacity.jwdnd.course1.cloudstorage.entity.FileEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

public interface IFileService {
    void saveFile(MultipartFile file, Integer userId) throws IOException;

    List<FileEntity> getAllFiles(Integer userId);

    void deleteFile(Integer fileId, Integer userId);

    void download(Integer fileId, HttpServletResponse response, Integer userId) throws IOException;
}
