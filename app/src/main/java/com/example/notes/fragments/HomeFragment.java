package com.example.notes.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.notes.R;
import com.example.notes.adapter.NotesAdapter;
import com.example.notes.viewmodel.NotesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.

 */
public class HomeFragment extends Fragment {
    NotesViewModel notesViewModel;
    RecyclerView notesRecycler;
    NotesAdapter adapter;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        FloatingActionButton viewById = (FloatingActionButton) view.findViewById(R.id.action_button);
       notesViewModel= new ViewModelProvider(getActivity()).get(NotesViewModel.class);


        viewById .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager=getActivity().getSupportFragmentManager();


                Log.i("FloatingActionButtion","clicked");

                fragmentManager.beginTransaction().setCustomAnimations(R.anim.zoom_in,R.anim.zoom_out).replace(R.id.fragment_container,AddNotesFragment.class,null).addToBackStack("home").setReorderingAllowed(true).commit();
            }
        });

        notesRecycler=view.findViewById(R.id.NotesRecycler);



        notesViewModel.allNotesVM.observe(getActivity(), Notes ->{


            notesRecycler.setLayoutManager(new GridLayoutManager(getActivity(),2));
            adapter=new NotesAdapter(getActivity(),Notes);
            notesRecycler.setAdapter(adapter);


        });
        // Inflate the layout for this fragment
        return view;
    }
}