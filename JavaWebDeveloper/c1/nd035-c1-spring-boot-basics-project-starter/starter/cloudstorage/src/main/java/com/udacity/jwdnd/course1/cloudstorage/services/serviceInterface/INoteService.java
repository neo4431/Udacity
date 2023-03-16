package com.udacity.jwdnd.course1.cloudstorage.services.serviceInterface;

import com.udacity.jwdnd.course1.cloudstorage.entity.NoteEntity;
import com.udacity.jwdnd.course1.cloudstorage.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface INoteService {
    int createOrUpdateNote(NoteEntity note, Integer userId);

    List<NoteEntity> findAllByUserId(Integer userId);

    int deleteByNoteId(Integer noteId, Integer userId);
}
