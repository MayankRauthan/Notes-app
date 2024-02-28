package com.example.notes.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuHost;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.notes.LoggedInUseData.PreferenceManager;
import com.example.notes.R;
import com.example.notes.adapter.NotesAdapter;
import com.example.notes.viewmodel.NotesViewModel;
import com.example.notes.viewmodel.NotesViewModelFactory;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.

 */
public class HomeFragment extends Fragment {
    NotesViewModel notesViewModel;
    RecyclerView notesRecycler;
    String userId;
    NotesAdapter adapter;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notesViewModel = new ViewModelProvider(this, new NotesViewModelFactory(getActivity().getApplication(), new PreferenceManager(requireContext()).getUserId())).get(NotesViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        FloatingActionButton viewById = (FloatingActionButton) view.findViewById(R.id.action_button);
        // userId = getArguments().getString("userId");
        Log.w("ididid",new PreferenceManager(requireContext()).getUserId());



        viewById .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager=getActivity().getSupportFragmentManager();


                Log.i("FloatingActionButtion","clicked");
//                Bundle bundle=new Bundle();
//                bundle.putString("userId",userId);

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
                menuInflater.inflate(R.menu.log_out, menu);
            }



            @Override
            public void onMenuClosed(@NonNull Menu menu) {
                MenuProvider.super.onMenuClosed(menu);
            }

            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                // Handle menu item selection
                FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
                //notesViewModel.reset();
                notesViewModel.rem(getViewLifecycleOwner());

                Bundle data=new Bundle();
                data.putBoolean("logged_out",true);
                fragmentManager.beginTransaction().setCustomAnimations(R.anim.zoom_in,R.anim.zoom_out).replace(R.id.fragment_container,LoginFragment.class,data).setReorderingAllowed(true).commit();
                return true;
            }
        }, getViewLifecycleOwner(), Lifecycle.State.RESUMED);
    }






}