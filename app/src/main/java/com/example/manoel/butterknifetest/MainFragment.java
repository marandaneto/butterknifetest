package com.example.manoel.butterknifetest;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by manoel on 07/12/15.
 */
public class MainFragment extends Fragment {

//    @Bind(R.id.button1)
//    Button button1;
//    @Bind(R.id.button2)
//    Button button2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);
        ButterKnife.bind(this, view);
        // TODO Use fields...
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
