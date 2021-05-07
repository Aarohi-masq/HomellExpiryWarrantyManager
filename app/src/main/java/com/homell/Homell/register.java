package com.homell.Homell;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.homell.Homell.R;

public class register extends AppCompatActivity {
    EditText email;
    EditText pass;
    EditText name;
    TextView signin;
    RelativeLayout verifyemail,form;
    Button reg;
    FirebaseAuth fAuthreg;

    @Override
    public void onBackPressed() {

        FirebaseAuth.getInstance().signOut();
        finish();
        startActivity(new Intent(register.this,Login.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email = (EditText) findViewById(R.id.emreg);
        pass = (EditText) findViewById(R.id.passreg);
        name = (EditText) findViewById(R.id.namesignup);
        reg = (Button) findViewById(R.id.btnreg);
        verifyemail=(RelativeLayout) findViewById(R.id.verifyemail);
        form= (RelativeLayout) findViewById(R.id.form);
        fAuthreg = (FirebaseAuth) FirebaseAuth.getInstance();
        signin = (TextView) findViewById(R.id.sigintxt);
        final LoadingAlertDialog d1=new LoadingAlertDialog(register.this);

        fAuthreg = FirebaseAuth.getInstance();


        reg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                    final String emailv = email.getText().toString();
                    String pass1=pass.getText().toString();
                    final String regname=name.getText().toString();

                d1.loadingdialog();
                Handler handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        d1.dismissDialog();
                    }
                },2000);

                if(TextUtils.isEmpty(emailv)){
                    email.setError("Email is required");
                    return;
                }

                if(TextUtils.isEmpty(pass1)){
                    pass.setError("Password is required");
                    return;
                }

                if( TextUtils.isEmpty(regname) ) {
                    name.setError("Name is required");
                    return;
                }


                if(pass1.length()<8){
                    pass.setError("Password must be atleast of 8 characters ");
                    Toast.makeText(register.this, "Add Upper case, Lower case with special character to make your password stronger", Toast.LENGTH_SHORT).show();
                    return;
                }


                        fAuthreg.createUserWithEmailAndPassword(email.getText().toString(), pass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    fAuthreg.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(register.this, "Please check your email to verify", Toast.LENGTH_SHORT).show();
                                                verifyemail.setVisibility(View.VISIBLE);
                                                form.setVisibility(View.GONE);


                                                user user1=new user(regname, emailv);
                                                FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user1);
                                                email.setText("");
                                                pass.setText("");
                                                name.setText("");

                                                FirebaseAuth.getInstance().signOut();
                                            } else {
                                                Toast.makeText(register.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });

                                } else {
                                    Toast.makeText(register.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });





            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(register.this, Login.class);
                startActivity(intent1);
            }
        });
    }


}
