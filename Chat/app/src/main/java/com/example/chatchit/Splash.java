package com.example.chatchit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static java.lang.Thread.sleep;

public class Splash extends AppCompatActivity {
    private  static int SPLASH_SCREEN = 3000;
    private FirebaseAuth mAuth;
    Animation topAnim, bottomAnim;
    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mAuth = FirebaseAuth.getInstance();

        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        logo = findViewById(R.id.logo_image);

        logo.setAnimation(topAnim);

        FirebaseUser currentUser = mAuth.getCurrentUser();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try
                {
                    sleep(2000);
                    if (currentUser == null)
                    {
                        Intent i = new Intent(Splash.this, LoginActivity.class);
                        startActivity(i);
                    }
                    else
                    {
                        Intent i = new Intent(Splash.this, MainActivity.class);
                        startActivity(i);
                    }
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

}