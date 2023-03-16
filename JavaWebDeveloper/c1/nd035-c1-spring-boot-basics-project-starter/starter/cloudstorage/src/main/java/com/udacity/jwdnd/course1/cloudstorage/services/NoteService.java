package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.entity.NoteEntity;
import com.udacity.jwdnd.course1.cloudstorage.exception.AccessDeniedException;
import com.udacity.jwdnd.course1.cloudstorage.exception.DataNotFoundException;
import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.services.serviceInterface.INoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class NoteService implements INoteService {
    @Autowired
    private NoteMapper noteMapper;

    @Override
    public int createOrUpdateNote(NoteEntity note, Integer userId) {
        if(Objects.isNull(note.getNoteId())) {
            note.setUserId(userId);
            return noteMapper.insert(note);
        }
        return noteMapper.update(note);
    }

    @Override
    public List<NoteEntity> findAllByUserId(Integer userId) {
        return noteMapper.findAllByUserId(userId);
    }

    @Override
    public int deleteByNoteId(Integer noteId, Integer userId) {
        NoteEntity noteEntity = noteMapper.findByNoteId(noteId)
                .orElseThrow(() -> new DataNotFoundException("This note does not exist!!!"));

        if(noteEntity.getUserId() != userId) {
            throw new AccessDeniedException("Access denied!!!");
        }

        return noteMapper.deleteByNoteId(noteId);
    }
}
