package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.entity.CredentialEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CredentialMapper {

    @Select("select * from CREDENTIALS where CREDENTIALID = #{credentialId}")
    Optional<CredentialEntity> findByCredentialId(Integer credentialId);

    @Select("select * from CREDENTIALS where USERID = #{userId}")
    List<CredentialEntity> findAllByUserid(Integer userId);

    @Insert("insert into CREDENTIALS (URL, USERNAME, KEY, PASSWORD, USERID) " +
            "values (#{url},#{username}, #{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int insert(CredentialEntity credential);

    @Update("update CREDENTIALS " +
            "set URL = #{url}, " +
            "    USERNAME = #{username}, " +
            "    PASSWORD = #{password} " +
            "where " +
            "    CREDENTIALID = #{credentialId}")
    int update(CredentialEntity credential);

    @Delete("delete from CREDENTIALS where CREDENTIALID = #{credentialId}")
    int deleteByCredentialId(Integer credentialId);
}
