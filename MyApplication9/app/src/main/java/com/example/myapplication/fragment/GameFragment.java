package com.example.myapplication.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.button.MyImageButton;

import java.util.Random;

public class GameFragment extends Fragment {
    private MyImageButton[][] cards;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_game, container, false);

        cards = new MyImageButton[4][9];
        HideLis hideLis = new HideLis();
        int start = R.id.ib_card_00;
        for (int j = 0; j < 9; ++j) {
            cards[0][j] = view.findViewById(start + j);
            cards[0][j].setOnClickListener(hideLis);
            cards[0][j].setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        }
        cards[1][0] = view.findViewById(R.id.ib_card_09);
        cards[1][0].setOnClickListener(hideLis);
        cards[1][0].setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        start = R.id.ib_card_10 - 1;
        int cnt = 9;
        for (int i = 1; i < cards.length; ++i) {
            for (int j = 0; j < cards[i].length; ++j) {
                if (i == 1 && j == 0)
                    continue;
                int id = start + (i - 1) * 9 + j;
                cards[i][j] = view.findViewById(id);
                cards[i][j].setOnClickListener(hideLis);
                cards[i][j].setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            }
        }
        int a = new Random().nextInt(3);
        Log.e("random", "" + a);
        switch (a) {
            case 0:
                initCardFirstPattern();
                break;
            case 1:
                initCardSecondPattern();
                break;
            case 2:
                initCardThirdPattern();
                break;
        }
        return view;
    }

    private class HideLis implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            ((MyImageButton) view).setClicked(true);
            ((MyImageButton) view).setVisibility(View.INVISIBLE);
        }
    }

    public void reset() {
        for (int i = 0; i < cards.length; ++i) {
            for (int j = 0; j < cards[i].length; j++) {
                cards[i][j].setVisibility(View.VISIBLE);
                cards[i][j].setClicked(false);
            }
        }
    }

    private void initCardFirstPattern() {
        cards[0][0].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.food1));
        cards[0][1].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.face1));
        cards[0][2].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.face3));
        cards[0][3].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.transport2));
        cards[0][4].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.face3));
        cards[0][5].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.face1));
        cards[0][6].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.face3));
        cards[0][7].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.face1));
        cards[0][8].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.face6));

        cards[1][0].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.house4));
        cards[1][1].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.house2));
        cards[1][2].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.house1));
        cards[1][3].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.house1));
        cards[1][4].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.house2));
        cards[1][5].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.food9));
        cards[1][6].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.house9));
        cards[1][7].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.house4));
        cards[1][8].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.house1));

        cards[2][0].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.transport2));
        cards[2][1].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.transport6));
        cards[2][2].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.transport1));
        cards[2][3].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.transport2));
        cards[2][4].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.transport3));
        cards[2][5].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.transport5));
        cards[2][6].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.food1));
        cards[2][7].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.transport6));
        cards[2][8].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.food2));

        cards[3][0].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.food9));
        cards[3][1].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.food2));
        cards[3][2].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.food8));
        cards[3][3].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.food2));
        cards[3][4].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.food1));
        cards[3][5].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.food5));
        cards[3][6].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.food4));
        cards[3][7].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.food7));
        cards[3][8].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.house5));

        cards[0][2].setRight(true);
        cards[0][5].setRight(true);
        cards[0][8].setRight(true);

        cards[1][0].setRight(true);
        cards[1][4].setRight(true);
        cards[1][6].setRight(true);

        cards[2][2].setRight(true);
        cards[2][3].setRight(true);
        cards[2][7].setRight(true);

        cards[3][2].setRight(true);
        cards[3][3].setRight(true);
        cards[3][6].setRight(true);

        ((MainActivity) getActivity()).initCardFirstPattern();
    }
    private void initCardSecondPattern() {
        cards[0][0].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.face7));
        cards[0][1].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.face4));
        cards[0][2].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.face5));
        cards[0][3].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.face5));
        cards[0][4].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.face8));
        cards[0][5].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.face3));
        cards[0][6].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.face1));
        cards[0][7].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.face7));
        cards[0][8].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.face3));

        cards[1][0].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.house2));
        cards[1][1].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.house6));
        cards[1][2].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.house4));
        cards[1][3].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.house3));
        cards[1][4].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.house1));
        cards[1][5].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.house6));
        cards[1][6].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.house3));
        cards[1][7].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.house4));
        cards[1][8].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.house2));

        cards[2][0].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.transport9));
        cards[2][1].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.transport5));
        cards[2][2].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.transport7));
        cards[2][3].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.transport7));
        cards[2][4].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.transport1));
        cards[2][5].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.transport2));
        cards[2][6].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.transport7));
        cards[2][7].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.transport5));
        cards[2][8].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.transport1));

        cards[3][0].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.food4));
        cards[3][1].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.food1));
        cards[3][2].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.food7));
        cards[3][3].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.food6));
        cards[3][4].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.food5));
        cards[3][5].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.food3));
        cards[3][6].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.food1));
        cards[3][7].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.food2));
        cards[3][8].setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.food4));

        cards[0][0].setRight(true);
        cards[0][3].setRight(true);
        cards[0][8].setRight(true);

        cards[1][1].setRight(true);
        cards[1][4].setRight(true);
        cards[1][6].setRight(true);

        cards[2][0].setRight(true);
        cards[2][3].setRight(true);
        cards[2][7].setRight(true);

        cards[3][2].setRight(true);
        cards[3][5].setRight(true);
        cards[3][8].setRight(true);

        ((MainActivity) getActivity()).initCardSecondPattern();
    }



    private void initCardThirdPattern() {
        initCardFirstPattern();
    }

    public boolean checkResult() {
        for (int i = 0; i < cards.length; i++) {
            for (int j = 0; j < cards[i].length; j++) {
                if (cards[i][j].isRight() && cards[i][j].isClicked() || !cards[i][j].isRight() && !cards[i][j].isClicked())
                    return false;
            }
        }
        return true;
    }
}
