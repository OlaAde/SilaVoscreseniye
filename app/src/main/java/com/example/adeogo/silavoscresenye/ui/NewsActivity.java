package com.example.adeogo.silavoscresenye.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.adeogo.silavoscresenye.R;

/**
 * Created by Adeogo on 10/2/2017.
 */

public class NewsActivity extends AppCompatActivity {

    private TextView mTitleTextView;
    private TextView mContentTextView;
    private TextView mDateTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mContentTextView = (TextView) findViewById(R.id.tv_content);
        mTitleTextView = (TextView) findViewById(R.id.title_tv);
        mDateTextView = (TextView) findViewById(R.id.date_tv);

        mDateTextView.setText("17.09.17");
        mContentTextView.setText("Очень часто мы говорим о том, как достичь спасения. Но нужно говорить и о том, что следует после спасения. Ведь с принятием Христа христианская жизнь только начинается, и мы должны понять, что такое христианская жизнь. Во второй главе послания к Ефесянам говорится: Ибо благодатью вы спасены через веру, и сие не от вас, Божий дар: не от дел, чтобы никто не хвалился. Итак, мы спасаемся благодаря Божьей благодати, его доброте. Это не то, что мы заслужили, это то, что мы получаем абсолютно бесплатно; чтобы никто не хвалился, чтобы никто не мог сказать: «Я сделал так много, Бог увидел это и дал мне спасение». Спасение – это дар для всех. Господь предложил Иисуса Христа как жертву за наши грехи, и все, что нужно, – принять это через веру. Иногда люди спрашивают: «Неужели даже самый отъявленный грешник может принять спасение?» И я говорю – «да». Даже тот, кто, как тебе кажется, не заслуживает этого дара, может принять его, потому что это дар для всех. Он не от дел: не от того, что кто-то хуже или лучше. Этот дар через Иисуса Христа был дан человечеству, и мы принимаем его. Но на этом все не заканчивается. Далее в послании к Ефесянам говорится:");
        mTitleTextView.setText("Спасен, а что дальше?");
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
