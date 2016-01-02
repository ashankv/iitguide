package com.iitguide.iitguide;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.CardView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ashank on 12/31/2015.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder>{

    ArrayList<Professor> professors;

    public RVAdapter(ArrayList<Professor> professors){
        this.professors = professors;
    }


    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_row, parent, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder holder, int position) {
        holder.nameTextView.setText(professors.get(position).nameSubject);
        holder.descripTextView.setText(professors.get(position).booksWritten);
        holder.picView.setImageResource(professors.get(position).photoId);
    }

    @Override
    public int getItemCount() {
        return professors.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    public static class PersonViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView nameTextView;
        TextView descripTextView;
        ImageView picView;

        PersonViewHolder(View itemView){

            super(itemView);
            cardView = (CardView)itemView.findViewById(R.id.cardView);
            nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
            descripTextView = (TextView) itemView.findViewById(R.id.descripTextView);
            picView = (ImageView) itemView.findViewById(R.id.picView);

        }
    }
}
