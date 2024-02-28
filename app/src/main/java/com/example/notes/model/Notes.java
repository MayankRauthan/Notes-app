package com.example.notes.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "mayank")
public class Notes {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String userId;
    public String notesTitle;
    public String notesData;

}
