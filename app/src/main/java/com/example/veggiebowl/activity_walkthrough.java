package com.example.veggiebowl;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TableLayout;

import java.util.ArrayList;
import java.util.List;

public class activity_walkthrough extends AppCompatActivity {

    private ViewPager screenPager;
IntroViewPagerAdapter introViewPagerAdapter;
Button button;
Button button2;
int position=0;
TabLayout tabindicator;
Animation btnanim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walkthrough);

        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        final List<screen> mList= new ArrayList<>();
        //mList.add(new screen("Know how much to add and when","",R.drawable.image3));
        mList.add(new screen("Monitoring made Easy","",R.drawable.images2));
        mList.add(new screen("Know your plant growth and its different stages","",R.drawable.images1));
        mList.add(new screen("Enjoy your harvest","",R.drawable.images4));
        button=findViewById(R.id.button);
        button2=findViewById(R.id. button2);
        btnanim= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.button_animation);
        screenPager=findViewById(R.id.screenpager);
        tabindicator=findViewById(R.id.tabindicator);
        introViewPagerAdapter=new IntroViewPagerAdapter(this,mList);
        screenPager.setAdapter(introViewPagerAdapter);
        tabindicator.setupWithViewPager(screenPager);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                position=screenPager.getCurrentItem();
                if(position<mList.size())
                {
                    position++;
                    screenPager.setCurrentItem(position);
                }

                if(position==mList.size()-1)
                {
                    loadlastscreen();
                }


            }
        });


        tabindicator.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == mList.size() - 1) {
                    loadlastscreen();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 Intent intent=new Intent(activity_walkthrough.this,signin_activity.class);
                startActivity(intent);


            }
        });
        }

        void loadlastscreen() {

        button.setVisibility(View.INVISIBLE);
        button2.setVisibility(View.VISIBLE);
        tabindicator.setVisibility(View.INVISIBLE);
        button2.setAnimation(btnanim);

        }
}
