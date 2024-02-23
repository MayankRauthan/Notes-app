package com.example.notes.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.notes.R;
import com.example.notes.model.Notes;
import com.example.notes.viewmodel.NotesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddNotesFragment extends Fragment {
    String title,notes;
    NotesViewModel notesViewModel;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    public AddNotesFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_notes, container, false);
        // Inflate the layout for this fragment



        return view;
    }

    @Override
    public void onViewCreated(@NonNull View views, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(views, savedInstanceState);
        notesViewModel= new ViewModelProvider(getActivity()).get(NotesViewModel.class);
        ((FloatingActionButton)views.findViewById(R.id.saveButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = String.valueOf(((EditText) views.findViewById(R.id.titleEditText)).getText());
                notes = String.valueOf(((EditText) views.findViewById(R.id.NotesEditText)).getText());
                if(!title.trim().isEmpty()&&!notes.trim().isEmpty()){
                    createNotes(title,notes);
                    getParentFragmentManager().popBackStack();
                }
                else
                    Toast.makeText(getContext(),"Empty notes",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void createNotes(String title, String note)
    {
        Notes notes1=new Notes();
        notes1.notesTitle=title;
        notes1.notesData=note;
       notesViewModel.insertNotesVM(notes1);
    }

}