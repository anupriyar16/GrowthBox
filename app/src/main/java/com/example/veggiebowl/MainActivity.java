package com.example.veggiebowl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
private TextView tv;
private ImageView iv;
private TextView tv1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=(TextView) findViewById(R.id.tv);
        tv1=(TextView) findViewById(R.id.tv1);
        iv=(ImageView) findViewById(R.id.iv);
        Animation anim= AnimationUtils.loadAnimation(this,R.anim.trans);
        tv.startAnimation(anim);
        iv.startAnimation(anim);
        tv1.startAnimation(anim);

        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(2500);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
                finally{
                    Intent i = new Intent(getApplicationContext(),activity_walkthrough.class);
                    startActivity(i);
                    finish();
                }
            }
        };
        timer.start();


    }
}
