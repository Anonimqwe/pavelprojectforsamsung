package com.example.myapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.dataBase.ResultsDBOpenHelper;
import com.example.myapplication.model.Results;
import com.example.myapplication.recycler.ResultsAdapter;
import com.google.android.material.button.MaterialButton;

import java.util.Comparator;
import java.util.List;

public class MenuFragment extends Fragment {
    private EditText editTextUsername;
    private MaterialButton mbUsername;
    private RecyclerView recyclerView;
    private MainActivity mainActivity;
    private CheckBox musicOnOff;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mainActivity = ((MainActivity)getActivity());
        ResultsDBOpenHelper resultsDBOpenHelper = new ResultsDBOpenHelper(getContext());
        List<Results> resultsList = resultsDBOpenHelper.findAll();
        resultsList.sort(new Comparator<Results>() {
            @Override
            public int compare(Results r1, Results r2) {
                return r1.getTime().compareTo(r2.getTime());
            }
        });
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        recyclerView = view.findViewById(R.id.rv_top_list);
        ResultsAdapter resultsAdapter = new ResultsAdapter();
        recyclerView.setAdapter(resultsAdapter);
        resultsAdapter.setResultsList(resultsList);
        mbUsername = view.findViewById(R.id.mb_username);
        mbUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.saveUsername(editTextUsername.getText().toString());
            }
        });
        musicOnOff = view.findViewById(R.id.cb_media);
        musicOnOff.setChecked(mainActivity.isEnableMusic());
        musicOnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (musicOnOff.isChecked()) {
                    mainActivity.startMusic();
                }
                else {
                    mainActivity.pauseMusic();
                }
                mainActivity.saveEnableMusic(musicOnOff.isChecked());
                mainActivity.setEnableMusic(musicOnOff.isChecked());
            }
        });
        editTextUsername = view.findViewById(R.id.et_username);
        String username = mainActivity.loadUsername();
        if (!username.equals("no name")) {
            editTextUsername.setText(username);
        }
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mainActivity.saveEnableMusic(musicOnOff.isChecked());
    }
}
