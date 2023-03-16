package com.udacity.jwdnd.course1.cloudstorage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteEntity {
    private Integer noteId;
    private String noteTitle;
    private String noteDescription;
    private Integer userId;
}
