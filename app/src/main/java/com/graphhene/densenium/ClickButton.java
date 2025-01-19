package com.graphhene.densenium;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.github.sshadkany.neo;

public class ClickButton extends AppCompatActivity {

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clickbutton);

        final neo mybtn = findViewById(R.id.my_button);
        ViewGroup viewGroup = findViewById(R.id.my_button);

        final MediaPlayer sound = MediaPlayer.create(this,R.raw.mousescrolling);

        final ImageView imageview = (ImageView) viewGroup.getChildAt(0);
        mybtn.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (mybtn.isShapeContainsPoint(event.getX(), event.getY())) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            sound.start();
                            mybtn.setStyle(neo.small_inner_shadow);
                            imageview.setScaleX(imageview.getScaleX() * 0.9f);
                            imageview.setScaleY(imageview.getScaleY() * 0.9f);
                            return true;
                        case MotionEvent.ACTION_UP:
                        case MotionEvent.ACTION_CANCEL:
                            mybtn.setStyle(neo.drop_shadow);
                            imageview.setScaleX(1);
                            imageview.setScaleY(1);
                            return true;
                    }
                }
                return false;
            }
        });
    }
}