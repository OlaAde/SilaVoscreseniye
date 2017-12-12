package com.example.adeogo.silavoscresenye.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.adeogo.silavoscresenye.R;

/**
 * Created by Adeogo on 9/12/2017.
 */

public class OfferingsFragment extends Fragment {

    private Button mTitheButton;
    private Button mBuildingOfferings;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_offerings, container, false);
        mTitheButton = (Button) rootView.findViewById(R.id.tithe);
        mBuildingOfferings = (Button) rootView.findViewById(R.id.building);

        mBuildingOfferings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), OfferingsActivity.class);
                intent.putExtra("state", false);
                startActivity(intent);
            }
        });

        mTitheButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), OfferingsActivity.class);
                intent.putExtra("state", true);
                startActivity(intent);
            }
        });

        return rootView;
    }

}