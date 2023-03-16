package com.udacity.jwdnd.course1.cloudstorage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileEntity {
    private Integer fileId;
    private String fileName;
    private String contentType;
    private String fileSize;
    private Integer userid;
    private byte[] fileData;
}
