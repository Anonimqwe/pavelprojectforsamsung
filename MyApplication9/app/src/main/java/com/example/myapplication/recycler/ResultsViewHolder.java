package com.example.myapplication.recycler;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Results;

public class ResultsViewHolder extends RecyclerView.ViewHolder {
    private View view;

    public ResultsViewHolder(View view) {
        super(view);
        this.view = view;
    }
    public void bind (Results results, int position) {
        TextView textViewNumber = view.findViewById(R.id.text_view_number);
        textViewNumber.setText(String.valueOf(position));

        TextView textViewName = view.findViewById(R.id.text_view_name);
        textViewName.setText(results.getName());

        TextView textViewTime = view.findViewById(R.id.text_view_time);
        textViewTime.setText(results.getTime());
    }

}
