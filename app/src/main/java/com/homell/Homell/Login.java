package com.homell.Homell;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.homell.Homell.R;

public class Login extends AppCompatActivity {
    EditText Email;
    EditText Password;
    TextView Info;
    Button Login;
    FirebaseAuth fAuth;
    int counter=3;
    TextView ForgotPass;
    TextView signup;
    private BroadcastReceiver OfflineReceiver3 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_login);
        Email = (EditText) findViewById(R.id.em);
        Password = (EditText) findViewById(R.id.pass);
        Info = (TextView) findViewById(R.id.atinfo);
        Login = (Button) findViewById(R.id.btnlogin);
        fAuth = (FirebaseAuth) FirebaseAuth.getInstance();
        ForgotPass =(TextView) findViewById(R.id.fgpas);
        signup = (TextView) findViewById(R.id.signupcreate);
        final LoadingAlertDialog d=new LoadingAlertDialog(Login.this);






        Login.setOnClickListener(new View.OnClickListener() {
            //Date currentTime = Calendar.getInstance().getTime();

            @Override
            public void onClick(View v) {






                String email= Email.getText().toString().trim();
                String password = Password.getText().toString().trim();



                if(TextUtils.isEmpty(email)){
                    Email.setError("Email is required");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    Password.setError("Password is required");
                    return;
                }

                if(password.length()<6){
                    Password.setError("Password must be atleast of 6 characters ");
                    return;
                }
                d.loadingdialog();
                Handler handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        d.dismissDialog();
                    }
                },2000);

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            if(fAuth.getCurrentUser().isEmailVerified())
                            {
                                Toast.makeText(Login.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                                startActivity( new Intent(getApplicationContext(),home.class));

                            }
                            else
                            {
                                Toast.makeText(Login.this, "Please verify your email address", Toast.LENGTH_SHORT).show();
                            }

                        }
                        else {
                            counter--;
                            Info.setVisibility(View.VISIBLE);

                            Info.setText("No. of attempts remaining: "+String.valueOf(counter));
                            Toast.makeText(Login.this, "Please check your email and password", Toast.LENGTH_SHORT).show();
                            if(counter==0){
                            Login.setEnabled(false);
                            }

                        }

                    }
                });
            }
        });
        ForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(Login.this, forgotpwd.class);
                startActivity(intent);
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(Login.this, register.class);
                startActivity(intent1);
            }
        });


    }



}
