package com.example.notes.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuHost;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notes.R;
import com.example.notes.model.Notes;
import com.example.notes.viewmodel.NotesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "title";
    private static final String ARG_PARAM2 = "Notes";
    // TODO: Rename and change types of parameters
    private int id;
    private String titles;
    private String note;
    private NotesViewModel notesViewModel;

    public UpdateFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            titles = getArguments().getString(ARG_PARAM1);
            note = getArguments().getString(ARG_PARAM2);
            id=getArguments().getInt("id");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_update, container, false);

        EditText title = (EditText) view.findViewById(R.id.updatetitleEditText);
        EditText notes = (EditText) view.findViewById(R.id.UpdateNotesEditText);
        title.setText(this.titles);
        notes.setText(this.note);
        notesViewModel= new ViewModelProvider(getActivity()).get(NotesViewModel.class);
        ((FloatingActionButton)view.findViewById(R.id.updatesaveButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = String.valueOf(((EditText) view.findViewById(R.id.updatetitleEditText)).getText());
                String notes = String.valueOf(((EditText) view.findViewById(R.id.UpdateNotesEditText)).getText());
                if (!title.trim().isEmpty() && !notes.trim().isEmpty()) {
                    UpdateNotes(title, notes, id);
                    getParentFragmentManager().popBackStack();
                } else
                    Toast.makeText(getContext(), "Empty notes", Toast.LENGTH_SHORT).show();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
    private void UpdateNotes(String title, String notes,int id) {
        Notes notes1=new Notes();
        notes1.id=id;
        notes1.notesTitle=title;
        notes1.notesData=notes;
        notesViewModel.updateNotesVM(notes1);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MenuHost menuHost = requireActivity();
        menuHost.addMenuProvider(new MenuProvider() {
            @Override
            public void onPrepareMenu(@NonNull Menu menu) {
                MenuProvider.super.onPrepareMenu(menu);
            }

            @Override
            public void onCreateMenu(Menu menu, MenuInflater menuInflater) {
                menuInflater.inflate(R.menu.delete_menu, menu);
            }



            @Override
            public void onMenuClosed(@NonNull Menu menu) {
                MenuProvider.super.onMenuClosed(menu);
            }

            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                // Handle menu item selection
                notesViewModel.deleteNodesVM(id);
                getParentFragmentManager().popBackStack();
                return true;
            }
        }, getViewLifecycleOwner(), Lifecycle.State.RESUMED);
    }
    }
