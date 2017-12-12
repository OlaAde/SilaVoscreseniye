package com.example.adeogo.silavoscresenye.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.adeogo.silavoscresenye.R;
import com.example.adeogo.silavoscresenye.adapter.ViewPagerAdapter;

public class AddNoteActivity extends AppCompatActivity {

    private static final String TAG = "AddNoteActivity";
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

//        viewPager = (ViewPager) findViewById(R.id.htab_viewpager);
//
//        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
//        tabLayout.setupWithViewPager(viewPager);
//
//        setupViewPager(viewPager);

//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//           @Override
//           public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//           }
//
//           @Override
//           public void onPageSelected(int position) {
//               Toast.makeText(AddNoteActivity.this, "You have selected tab " + (position+1),  Toast.LENGTH_SHORT).show();
//
//           }
//
//           @Override
//           public void onPageScrollStateChanged(int state) {
//
//           }
//       });
        NotesFragment notesFragment = new NotesFragment();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .add(R.id.new_note_fragment, notesFragment)
                .commit();


    }


//    private void setupViewPager(ViewPager viewPager) {
//        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
//        adapter.addFrag(new NotesFragment(), "First");
//        adapter.addFrag(new NotesFragment(), "Second");
//        adapter.addFrag(new NotesFragment(), "Third");
//        viewPager.setAdapter(adapter);
//    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }


}
