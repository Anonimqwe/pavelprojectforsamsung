package com.example.myapplication.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Results;

import java.util.ArrayList;
import java.util.List;

public class ResultsAdapter extends RecyclerView.Adapter <ResultsViewHolder>{

    private List<Results> resultsList = new ArrayList<>();
    @NonNull
    @Override
    public ResultsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_results, parent, false);
        ResultsViewHolder resultsViewHolder = new ResultsViewHolder(view);
        return resultsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ResultsViewHolder holder, int position) {
        holder.bind(resultsList.get(position), position + 1);
    }

    @Override
    public int getItemCount() {
        return resultsList.size();
    }
    public void setResultsList (List<Results> results) {
        this.resultsList = results;
        notifyDataSetChanged();
    }

}
