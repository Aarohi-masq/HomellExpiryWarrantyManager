package com.homell.Homell;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import java.lang.*;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


public class forgotpwd extends AppCompatActivity
{
    private FirebaseAuth mAuth;
    private Button Reset;
    EditText Email;
    TextView needhelp;




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpwd);
        mAuth = FirebaseAuth.getInstance();
        Reset = (Button) findViewById(R.id.btnreset);
        needhelp = (TextView) findViewById(R.id.needhelp);
        needhelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),contactus.class);
                startActivity(intent);
            }
        });
        Email = (EditText) findViewById(R.id.emreset);
        Reset.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                String userEmail = Email.getText().toString();
                if (TextUtils.isEmpty(userEmail)) {
                    Toast.makeText(forgotpwd.this, "please write your valid email address", Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(forgotpwd.this, "Please check your email", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(forgotpwd.this, Login.class));
                            } else {
                                String message = task.getException().getMessage();
                                Toast.makeText(forgotpwd.this, "Error Occurred" + message, Toast.LENGTH_SHORT).show();


                            }
                        }
                    });
                }
            }
        });

    }
}