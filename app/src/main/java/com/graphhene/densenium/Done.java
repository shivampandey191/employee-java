package com.graphhene.densenium;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class Done extends AppCompatActivity {

    private static int SPLASH_SCREEN=5000;
    TextView Done;
    ImageView CheckMark;
    MediaPlayer player;

    AnimatedVectorDrawableCompat avd;
    AnimatedVectorDrawable avd2;

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_done);

        Done = findViewById(R.id.text_done);
        CheckMark = findViewById(R.id.checkMark);

        player = MediaPlayer.create(this,R.raw.completed);
        player.start();

        Drawable drawable = CheckMark.getDrawable();

        if(drawable instanceof AnimatedVectorDrawableCompat) {
            avd = (AnimatedVectorDrawableCompat) drawable;
            avd.start();
        }
        else if(drawable instanceof AnimatedVectorDrawable){
            avd2 = (AnimatedVectorDrawable) drawable;
            avd2.start();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Done.this,AddNew.class);
                Pair[] pairs = new Pair[2];
                pairs[0] = new Pair<View,String>(CheckMark,"checkmark");
                pairs[1] = new Pair<View,String>(Done,"done");

                ActivityOptions options =
                        ActivityOptions.makeSceneTransitionAnimation(Done.this,pairs);
                startActivity(intent,options.toBundle());
            }
        },SPLASH_SCREEN);

    }
}