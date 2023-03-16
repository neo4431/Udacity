package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.entity.NoteEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface NoteMapper {
    @Select("select NOTES.* " +
            "from NOTES " +
            "where USERID = #{userId}")
    List<NoteEntity> findAllByUserId(Integer userId);

    @Select("select * from NOTES where NOTEID = #{noteId}")
    Optional<NoteEntity> findByNoteId(Integer noteId);

    @Insert("insert into NOTES (NOTETITLE, NOTEDESCRIPTION, USERID) " +
            "values (#{noteTitle},#{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int insert(NoteEntity note);

    @Update("update NOTES " +
            "set NOTETITLE = #{noteTitle}, " +
            "    NOTEDESCRIPTION = #{noteDescription} " +
            "where " +
            "    NOTEID = #{noteId}")
    int update(NoteEntity note);

    @Delete("delete from NOTES where NOTEID = #{noteId}")
    int deleteByNoteId(Integer noteId);
}
