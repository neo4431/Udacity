package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.entity.FileEntity;
import com.udacity.jwdnd.course1.cloudstorage.exception.AccessDeniedException;
import com.udacity.jwdnd.course1.cloudstorage.exception.DataNotFoundException;
import com.udacity.jwdnd.course1.cloudstorage.exception.SameNameException;
import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.services.serviceInterface.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
public class FileService implements IFileService {

    @Autowired
    private FileMapper fileMapper;

    @Override
    public void saveFile(MultipartFile file, Integer userId) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if (fileMapper.findByFileNameAndUserId(fileName, userId).isPresent()) {
            throw new SameNameException("Filename already exists!!!");
        }

        byte[] bytes = file.getBytes();
        FileEntity fileEntity = new FileEntity();
        fileEntity.setFileName(fileName);
        fileEntity.setContentType(file.getContentType());
        fileEntity.setFileSize(String.valueOf(file.getSize()));
        fileEntity.setFileData(bytes);
        fileEntity.setUserid(userId);
        fileMapper.insert(fileEntity);
    }

    @Override
    public List<FileEntity> getAllFiles(Integer userId) {
        return fileMapper.findAllByUserId(userId);
    }

    @Override
    public void deleteFile(Integer fileId, Integer userId) {
        FileEntity fileEntity = fileMapper.findByFileId(fileId)
                .orElseThrow(() -> new DataNotFoundException("This file does not exist!!!"));

        if (fileEntity.getUserid() != userId) {
            throw new AccessDeniedException("Access denied!!!");
        }

        fileMapper.deleteByFileId(fileId);
    }

    @Override
    public void download(Integer fileId, HttpServletResponse response, Integer userId) throws IOException {
        FileEntity fileEntity = fileMapper.findByFileId(fileId)
                .orElseThrow(() -> new DataNotFoundException("File not found!!!"));

        if(fileEntity.getUserid() != userId) {
            throw new AccessDeniedException("Access denied!!!");
        }
        response.setContentType(fileEntity.getContentType());
        response.setHeader("Content-Disposition", "attachment; filename = " + fileEntity.getFileName());
        try (ServletOutputStream outputStream = response.getOutputStream()) {
            outputStream.write(fileEntity.getFileData());
        }
    }
}
