package com.example.adeogo.silavoscresenye.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.adeogo.silavoscresenye.R;

import org.w3c.dom.Text;

/**
 * Created by Adeogo on 8/11/2017.
 */

public class AboutUsFragment extends Fragment{

    private TextView mTextView1;
    private TextView mTextView2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_about_us, container, false);
        mTextView1 = (TextView) rootView.findViewById(R.id.about_us_tv_1);
        mTextView2 = (TextView) rootView.findViewById(R.id.about_us_tv_2);
        mTextView1.setText("Добро пожаловать в церковь \n" +
                "«Сила Воскресения».");

        mTextView2.setText("Если вы впервые на нашем сайте и только еще знакомитесь с церковью, то здесь вы найдете подробную информацию о нас и нашем служении. В нашей церкви есть место каждому, если Вы искренне ищете Бога и хотите видеть свою жизнь измененной, то эта церковь для вас! Что же Вы увидите и услышите, придя на наши воскресные богослужения? Во - первых понятное объяснение на современном русском языке Библейского послания. Во-вторых, прославление Бога на современных инструментах. В –третьих, если у вас есть какие-то особые нужды, скажем исцеление, мы молимся за них в конце собрания. Также Вы можете брать в церковь своих детей и во время собрания оставлять их в детской комнате, где с ними будут играть и заниматься их досугом (рисование, лепка и т.д.). На богослужениях можно присутствовать и не становясь постоянным членом церкви. Богослужения свободны для посещения всем желающим. Увидимся в ближайшее воскресение по адресу г. Казань, ул. Лаврентьева 17 на богослужении в 10:00, 12:00.");
        return rootView;
    }

}
