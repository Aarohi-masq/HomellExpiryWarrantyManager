package com.homell.Homell;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.homell.Homell.R;

public class SplashScreen extends AppCompatActivity {
    private static int SPLASH_TIMER=4000;

    ImageView splashIcon;
    TextView poweredby;
    TextView housenap;
    SharedPreferences onBoardingScreen;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        splashIcon = findViewById(R.id.splashicon);






        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                onBoardingScreen= getSharedPreferences("onBoardingScreen", MODE_PRIVATE);
                 boolean isFirstTime = onBoardingScreen.getBoolean("firstTime", true);

                 if(isFirstTime){

                     SharedPreferences.Editor editor =onBoardingScreen.edit();
                     editor.putBoolean("firstTime", false);
                     editor.commit();

                     Intent intent=new Intent(getApplicationContext(), OnBoarding.class);
                     startActivity(intent);
                     finish();
                 }
                 else{

                         FirebaseAuth firebaseAuth =FirebaseAuth.getInstance();
                         FirebaseUser firebaseUser =firebaseAuth.getCurrentUser();
                         if (firebaseUser!= null)
                         {
                             if(FirebaseAuth.getInstance().getCurrentUser().isEmailVerified()) {
                                 startActivity(new Intent(SplashScreen.this, home.class));
                             }
                         }else{
                             startActivity(new Intent(SplashScreen.this, Login.class));
                         }
                     }



                 }




        },SPLASH_TIMER);

    }
}
