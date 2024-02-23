package com.example.notes.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes.R;
import com.example.notes.fragments.UpdateFragment;
import com.example.notes.model.Notes;

import java.util.List;

public class NotesAdapter  extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    FragmentActivity activity;
    List<Notes> notes;
    public NotesAdapter(FragmentActivity activity, List<Notes> notes) {
    this.activity=activity;
    this.notes=notes;
    }


    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new NotesViewHolder(LayoutInflater.from(activity).inflate(R.layout.item_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        Notes n= notes.get(position);
        holder.title.setText(n.notesTitle);
        holder.notes.setText(n.notesData);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle data=new Bundle();
                data.putInt("id",n.id);
                data.putString("title",n.notesTitle);
                data.putString("Notes",n.notesData);

                activity.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_in,R.anim.zoom_out).replace(R.id.fragment_container, UpdateFragment.class,data).addToBackStack("we").setReorderingAllowed(true)
                        .commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    static class NotesViewHolder extends RecyclerView.ViewHolder {
        TextView title,notes;
        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.HomeTitle);
            notes=itemView.findViewById(R.id.HomeNotes);

        }
    }
}
