package com.example.notes.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.notes.Dao.NotesDao;
import com.example.notes.model.Notes;

@Database(entities = Notes.class,version = 1)
public abstract class NotesDatabase extends RoomDatabase {
    public abstract NotesDao notesDao();
    public static NotesDatabase Instance;
    public static NotesDatabase getInstance(Context context)
    {
        if(Instance==null){
            Instance= Room.databaseBuilder(context.getApplicationContext(),NotesDatabase.class,"mayank").allowMainThreadQueries().build();
        }
        return Instance;
    }

}
