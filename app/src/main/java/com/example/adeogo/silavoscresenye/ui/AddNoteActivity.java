package com.example.adeogo.silavoscresenye.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.adeogo.silavoscresenye.R;
import com.example.adeogo.silavoscresenye.adapter.ViewPagerAdapter;

public class AddNoteActivity extends AppCompatActivity {

    private static final String TAG = "AddNoteActivity";
     ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        viewPager = (ViewPager) findViewById(R.id.htab_viewpager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
        
        setupViewPager(viewPager);
       viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
           @Override
           public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

           }

           @Override
           public void onPageSelected(int position) {
               Toast.makeText(AddNoteActivity.this, Integer.toString(position), Toast.LENGTH_SHORT).show();

           }

           @Override
           public void onPageScrollStateChanged(int state) {

           }
       });


//        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//
//                viewPager.setCurrentItem(tab.getPosition());
//                setupViewPager(viewPager);
//
//                switch (tab.getPosition()) {
//                    case 0:
//                        break;
//                    case 1:
//                        Toast.makeText(AddNoteActivity.this, "Two", Toast.LENGTH_SHORT).show();
//                        break;
//                    case 2:
//                        Toast.makeText(AddNoteActivity.this, "Three", Toast.LENGTH_SHORT).show();
//                        break;
//                }
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//
//        });
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new NotesFragment(), "First");
        adapter.addFrag(new NotesFragment(), "First");
        adapter.addFrag(new NotesFragment(), "First");
        viewPager.setAdapter(adapter);
    }
    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.add_notes, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int idSelected = item.getItemId();
//        switch (idSelected){
//
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
