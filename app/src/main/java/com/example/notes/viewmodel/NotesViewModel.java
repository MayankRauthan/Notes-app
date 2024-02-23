package com.example.notes.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.notes.model.Notes;
import com.example.notes.repository.NotesRepo;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {

    public NotesRepo notesRepo;
    public LiveData<List<Notes>>allNotesVM;
    public NotesViewModel(@NonNull Application application) {
        super(application);
        notesRepo=new NotesRepo(application);
        allNotesVM=notesRepo.allnodes;
    }
    public void insertNotesVM(Notes notes)
    {
        notesRepo.insertNotes(notes);
    }
    public void deleteNodesVM(int id)
    {
        notesRepo.deleteNotes(id);
    }
    public void updateNotesVM(Notes node)
    {
        notesRepo.updateNotes(node);
    }

}
