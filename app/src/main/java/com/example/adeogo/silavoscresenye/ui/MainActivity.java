package com.example.adeogo.silavoscresenye.ui;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.adeogo.silavoscresenye.R;
import com.example.adeogo.silavoscresenye.utilities.JSONUtil;
import com.example.adeogo.silavoscresenye.utilities.NetworkUtil;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText mTestEditText;
    private TextView mTestTextView;
    private Button mShootButton;
    int chapterIndex = 0;
    private String versesListText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTestEditText = (EditText) findViewById(R.id.test_edit_text);
        mTestTextView = (TextView) findViewById(R.id.test_verse_list);
        mShootButton = (Button) findViewById(R.id.shoot_button);
        mShootButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fireUp();
            }
        });
    }

    private void fireUp() {
        String hereString = this.getString(R.string.app_name);
        chapterIndex = Integer.parseInt(mTestEditText.getText().toString());
        try {
            List<String> versesList = JSONUtil.getVersesList(JSONUtil.getChapterListObject(hereString, chapterIndex));
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < versesList.size(); i++) {
                stringBuilder.append(versesList.get(i));
            }
            versesListText = stringBuilder.toString();
            mTestTextView.setText((CharSequence) versesList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    class TestTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            String response = null;
            try {
                response = NetworkUtil.getResponseFromHttpUrl(NetworkUtil.buildUrl());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            chapterIndex = Integer.parseInt(mTestEditText.getText().toString());
            try {
                List<String> versesList = JSONUtil.getVersesList(JSONUtil.getChapterListObject(s, chapterIndex));
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < versesList.size(); i++) {
                    stringBuilder.append(versesList.get(i));
                }
                versesListText = stringBuilder.toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}




