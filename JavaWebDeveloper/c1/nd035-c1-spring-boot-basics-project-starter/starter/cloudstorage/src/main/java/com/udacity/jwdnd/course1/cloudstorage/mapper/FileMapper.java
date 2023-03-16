package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.entity.FileEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface FileMapper {

    @Select("select * from FILES where USERID = #{userId}")
    List<FileEntity> findAllByUserId(Integer userId);

    @Select("select * from FILES where FILEID = #{fileId}")
    Optional<FileEntity> findByFileId(Integer fileId);

    @Select("select * from FILES where FILENAME = #{fileName} and USERID = #{userId}")
    Optional<FileEntity> findByFileNameAndUserId(String fileName, Integer userId);

    @Insert("insert into FILES (FILENAME, CONTENTTYPE, FILESIZE, USERID, FILEDATA) " +
            "values (#{fileName}, #{contentType}, #{fileSize}, #{userid}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insert(FileEntity file);

    @Delete("delete from FILES where FILEID = #{fileId}")
    int deleteByFileId(Integer fileId);

}
