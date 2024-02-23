package com.example.notes.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.notes.model.Notes;

import java.util.List;

@Dao
public interface NotesDao {
    @Query("Select * from mayank")
    LiveData<List<Notes>> getAllNodes();

    @Insert
    void insertNotes(Notes...notes );

    @Query("Delete from mayank where id= :id")
    void deleteNotes(int id);

    @Update
    void updateNotes(Notes notes);

}
