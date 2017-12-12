package com.example.adeogo.silavoscresenye.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.adeogo.silavoscresenye.R;

/**
 * Created by Adeogo on 7/28/2017.
 */

public class RecordsFragment extends Fragment {



    private Context mContext;
    private Button mVideoButton;
    private Button mAudioButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_records, container, false);
        mAudioButton = (Button) rootView.findViewById(R.id.audioButton);
        mVideoButton = (Button) rootView.findViewById(R.id.videoButton);
        mContext = getContext();

        mAudioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AudioButton(v);
            }
        });

        mVideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VideoButton(v);
            }
        });
        return rootView;
    }

    public void AudioButton(View v){
        Intent intent =  new Intent(getActivity(), AudioActivity.class);
        startActivity(intent);
    }


    public void VideoButton(View v){
        Toast.makeText(mContext, "VideoButton", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), VideoActivity.class);
        startActivity(intent);
    }



}
