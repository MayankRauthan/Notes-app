package com.example.notes.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.notes.Dao.NotesDao;
import com.example.notes.Database.NotesDatabase;
import com.example.notes.model.Notes;

import java.util.List;

public class NotesRepo {
    NotesDao notesDao;
    public LiveData<List<Notes>> allnodes;
    public NotesRepo(Application app,String userId) {
        NotesDatabase notesDatabase=NotesDatabase.getInstance(app);
         notesDao=notesDatabase.notesDao();
       allnodes= notesDao.getAllNodes(userId);
    }
    public void insertNotes(Notes notes)
    {
        notesDao.insertNotes(notes);
    }
    public void deleteNotes(int id)
    {
        notesDao.deleteNotes(id);
    }
    public void updateNotes(Notes node)
    {
        notesDao.updateNotes(node);
    }

}
