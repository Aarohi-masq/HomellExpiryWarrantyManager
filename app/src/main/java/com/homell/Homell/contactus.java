package com.homell.Homell;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class contactus extends AppCompatActivity {

    ImageView backtn, sendmail,accdrop3,accdrop2,accdrop1,warrdrop1,warrdrop2,warrdrop3,warrdrop4,warrdrop5,warrdrop6,warrdrop7,expdrop1,expdrop2,expdrop3,expdrop4,expdrop5,expdrop6,expdrop7;
    TextView accans3,accans2,accans1,warrans1,warrans2,warrans3,warrans4,warrans5,warrans6,warrans7,expans1,expans2,expans3,expans4,expans5,expans6,expans7;
    RelativeLayout accheadques,expqueshead,warrheadques,warrlin1,warrlin2,warrlin3,warrlin4,warrlin5,warrlin6,warrlin7,explin1,explin2,explin3,explin4,explin5,explin6,explin7;
    LinearLayout accquesgrid,warrquesgrid,expquesgrid,acclin3,acclin2,acclin1;

    @Override

    public void onBackPressed() {
        Intent jintent = new Intent(contactus.this, home.class);
        startActivity(jintent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);

        sendmail = (ImageView) findViewById(R.id.sendmailbtn) ;
        warrdrop1 = (ImageView) findViewById(R.id.warrdrop1) ;
        warrdrop2 = (ImageView) findViewById(R.id.warrdrop2) ;
        warrdrop3 = (ImageView) findViewById(R.id.warrdrop3) ;
        warrdrop4 = (ImageView) findViewById(R.id.warrdrop4) ;
        warrdrop5 = (ImageView) findViewById(R.id.warrdrop5) ;
        warrdrop6 = (ImageView) findViewById(R.id.warrdrop6) ;
        warrdrop7 = (ImageView) findViewById(R.id.warrdrop7) ;
        expdrop1 = (ImageView) findViewById(R.id.expdrop1) ;
        expdrop2 = (ImageView) findViewById(R.id.expdrop2) ;
        expdrop3 = (ImageView) findViewById(R.id.expdrop3) ;
        expdrop4 = (ImageView) findViewById(R.id.expdrop4) ;
        expdrop5 = (ImageView) findViewById(R.id.expdrop5) ;
        expdrop6 = (ImageView) findViewById(R.id.expdrop6) ;

        accdrop3 = (ImageView) findViewById(R.id.accdrop3) ;
        accdrop2 = (ImageView) findViewById(R.id.accdrop2) ;
        accdrop1 = (ImageView) findViewById(R.id.accdrop1) ;

        sendmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","contactus@homll.me", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Need help in Using Homell App");

                startActivity(Intent.createChooser(emailIntent, "Send email..."));
            }
        });


        accheadques = (RelativeLayout) findViewById(R.id.accountheadques);
        warrheadques = (RelativeLayout) findViewById(R.id.warrheadques);
        expqueshead = (RelativeLayout) findViewById(R.id.expiryheadques);
        warrans1 = (TextView) findViewById(R.id.warrans1);
        warrans2 = (TextView) findViewById(R.id.warrans2);
        warrans3 = (TextView) findViewById(R.id.warrans3);
        warrans4 = (TextView) findViewById(R.id.warrans4);
        warrans5 = (TextView) findViewById(R.id.warrans5);
        warrans6 = (TextView) findViewById(R.id.warrans6);
        warrans7 = (TextView) findViewById(R.id.warrans7);

        expans1 = (TextView) findViewById(R.id.expans1);
        expans2 = (TextView) findViewById(R.id.expans2);
        expans3 = (TextView) findViewById(R.id.expans3);
        expans4 = (TextView) findViewById(R.id.expans4);
        expans5 = (TextView) findViewById(R.id.expans5);
        expans6 = (TextView) findViewById(R.id.expans6);

        accans3 = (TextView) findViewById(R.id.accans3);

        accans2 = (TextView) findViewById(R.id.accans2);
        accans1 = (TextView) findViewById(R.id.accans1);


        warrlin1 = (RelativeLayout) findViewById(R.id.warrlin1);
        warrlin2 = (RelativeLayout) findViewById(R.id.warrlin2);
        warrlin3 = (RelativeLayout) findViewById(R.id.warrlin3);
        warrlin4 = (RelativeLayout) findViewById(R.id.warrlin4);
        warrlin5 = (RelativeLayout) findViewById(R.id.warrlin5);
        warrlin6 = (RelativeLayout) findViewById(R.id.warrlin6);
        warrlin7 = (RelativeLayout) findViewById(R.id.warrlin7);

        explin1 = (RelativeLayout) findViewById(R.id.explin1);
        explin2 = (RelativeLayout) findViewById(R.id.explin2);
        explin3 = (RelativeLayout) findViewById(R.id.explin3);
        explin4 = (RelativeLayout) findViewById(R.id.explin4);
        explin5 = (RelativeLayout) findViewById(R.id.explin5);
        explin6 = (RelativeLayout) findViewById(R.id.explin6);



        accquesgrid = (LinearLayout) findViewById(R.id.accountquesgrid);
        acclin3 = (LinearLayout) findViewById(R.id.acclin3);
        acclin2 = (LinearLayout) findViewById(R.id.acclin2);
        acclin1 = (LinearLayout) findViewById(R.id.acclin1);
        warrquesgrid = (LinearLayout) findViewById(R.id.warrquesgrid);
        expquesgrid = (LinearLayout) findViewById(R.id.expiryquesgrid);

        explin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expdrop1.getRotation() == 180) {
                    expans1.setVisibility(View.GONE);
                    expdrop1.setRotation(0);
                    // Its visible
                    // Toast.makeText(home.this,"Taking Longer Than Usual", Toast.LENGTH_SHORT).show();
                } else {
                    expdrop1.setRotation(180);
                    expans1.setVisibility(View.VISIBLE);
                    // Either gone or invisible
                }

            }
        });
        explin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expdrop2.getRotation() == 180) {
                    expans2.setVisibility(View.GONE);
                    expdrop2.setRotation(0);
                    // Its visible
                    // Toast.makeText(home.this,"Taking Longer Than Usual", Toast.LENGTH_SHORT).show();
                } else {
                    expdrop2.setRotation(180);
                    expans2.setVisibility(View.VISIBLE);
                    // Either gone or invisible
                }

            }
        });
        explin3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expdrop3.getRotation() == 180) {
                    expans3.setVisibility(View.GONE);
                    expdrop3.setRotation(0);
                    // Its visible
                    // Toast.makeText(home.this,"Taking Longer Than Usual", Toast.LENGTH_SHORT).show();
                } else {
                    expdrop3.setRotation(180);
                    expans3.setVisibility(View.VISIBLE);
                    // Either gone or invisible
                }

            }
        });
        explin4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expdrop4.getRotation() == 180) {
                    expans4.setVisibility(View.GONE);
                    expdrop4.setRotation(0);
                    // Its visible
                    // Toast.makeText(home.this,"Taking Longer Than Usual", Toast.LENGTH_SHORT).show();
                } else {
                    expdrop4.setRotation(180);
                    expans4.setVisibility(View.VISIBLE);
                    // Either gone or invisible
                }

            }
        });
        explin5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expdrop5.getRotation() == 180) {
                    expans5.setVisibility(View.GONE);
                    expdrop5.setRotation(0);
                    // Its visible
                    // Toast.makeText(home.this,"Taking Longer Than Usual", Toast.LENGTH_SHORT).show();
                } else {
                    expdrop5.setRotation(180);
                    expans5.setVisibility(View.VISIBLE);
                    // Either gone or invisible
                }

            }
        });
        explin6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expdrop6.getRotation() == 180) {
                    expans6.setVisibility(View.GONE);
                    expdrop6.setRotation(0);
                    // Its visible
                    // Toast.makeText(home.this,"Taking Longer Than Usual", Toast.LENGTH_SHORT).show();
                } else {
                    expdrop6.setRotation(180);
                    expans6.setVisibility(View.VISIBLE);
                    // Either gone or invisible
                }

            }
        });


        warrlin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (warrdrop1.getRotation() == 180) {
                    warrans1.setVisibility(View.GONE);
                    warrdrop1.setRotation(0);
                    // Its visible
                    // Toast.makeText(home.this,"Taking Longer Than Usual", Toast.LENGTH_SHORT).show();
                } else {
                    warrdrop1.setRotation(180);
                    warrans1.setVisibility(View.VISIBLE);
                    // Either gone or invisible
                }

            }
        });

        warrlin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (warrdrop2.getRotation() == 180) {
                    warrans2.setVisibility(View.GONE);
                    warrdrop2.setRotation(0);
                    // Its visible
                    // Toast.makeText(home.this,"Taking Longer Than Usual", Toast.LENGTH_SHORT).show();
                } else {
                    warrdrop2.setRotation(180);
                    warrans2.setVisibility(View.VISIBLE);
                    // Either gone or invisible
                }

            }
        });
        warrlin3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (warrdrop3.getRotation() == 180) {
                    warrans3.setVisibility(View.GONE);
                    warrdrop3.setRotation(0);
                    // Its visible
                    // Toast.makeText(home.this,"Taking Longer Than Usual", Toast.LENGTH_SHORT).show();
                } else {
                    warrdrop3.setRotation(180);
                    warrans3.setVisibility(View.VISIBLE);
                    // Either gone or invisible
                }

            }
        });
        warrlin4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (warrdrop4.getRotation() == 180) {
                    warrans4.setVisibility(View.GONE);
                    warrdrop4.setRotation(0);
                    // Its visible
                    // Toast.makeText(home.this,"Taking Longer Than Usual", Toast.LENGTH_SHORT).show();
                } else {
                    warrdrop4.setRotation(180);
                    warrans4.setVisibility(View.VISIBLE);
                    // Either gone or invisible
                }

            }
        });
        warrlin5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (warrdrop5.getRotation() == 180) {
                    warrans5.setVisibility(View.GONE);
                    warrdrop5.setRotation(0);
                    // Its visible
                    // Toast.makeText(home.this,"Taking Longer Than Usual", Toast.LENGTH_SHORT).show();
                } else {
                    warrdrop5.setRotation(180);
                    warrans5.setVisibility(View.VISIBLE);
                    // Either gone or invisible
                }

            }
        });
        warrlin6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (warrdrop6.getRotation() == 180) {
                    warrans6.setVisibility(View.GONE);
                    warrdrop6.setRotation(0);
                    // Its visible
                    // Toast.makeText(home.this,"Taking Longer Than Usual", Toast.LENGTH_SHORT).show();
                } else {
                    warrdrop6.setRotation(180);
                    warrans6.setVisibility(View.VISIBLE);
                    // Either gone or invisible
                }

            }
        });
        warrlin7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (warrdrop7.getRotation() == 180) {
                    warrans7.setVisibility(View.GONE);
                    warrdrop7.setRotation(0);
                    // Its visible
                    // Toast.makeText(home.this,"Taking Longer Than Usual", Toast.LENGTH_SHORT).show();
                } else {
                    warrdrop7.setRotation(180);
                    warrans7.setVisibility(View.VISIBLE);
                    // Either gone or invisible
                }

            }
        });


        acclin3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (accdrop3.getRotation() == 180) {
                    accans3.setVisibility(View.GONE);
                    accdrop3.setRotation(0);
                    // Its visible
                    // Toast.makeText(home.this,"Taking Longer Than Usual", Toast.LENGTH_SHORT).show();
                } else {
                    accdrop3.setRotation(180);
                    accans3.setVisibility(View.VISIBLE);
                    // Either gone or invisible
                }

            }
        });
        accdrop2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (accdrop2.getRotation() == 180) {
                    accans2.setVisibility(View.GONE);
                    accdrop2.setRotation(0);
                    // Its visible
                    // Toast.makeText(home.this,"Taking Longer Than Usual", Toast.LENGTH_SHORT).show();
                } else {
                    accdrop2.setRotation(180);
                    accans2.setVisibility(View.VISIBLE);
                    // Either gone or invisible
                }

            }
        });
        acclin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (accdrop2.getRotation() == 180) {
                    accans2.setVisibility(View.GONE);
                    accdrop2.setRotation(0);
                    // Its visible
                    // Toast.makeText(home.this,"Taking Longer Than Usual", Toast.LENGTH_SHORT).show();
                } else {
                    accdrop2.setRotation(180);
                    accans2.setVisibility(View.VISIBLE);
                    // Either gone or invisible
                }

            }
        });

        accdrop3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (accdrop3.getRotation() == 180) {
                    accans3.setVisibility(View.GONE);
                    accdrop3.setRotation(0);
                    // Its visible
                    // Toast.makeText(home.this,"Taking Longer Than Usual", Toast.LENGTH_SHORT).show();
                } else {
                    accdrop3.setRotation(180);
                    accans3.setVisibility(View.VISIBLE);
                    // Either gone or invisible
                }

            }
        });

        acclin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (accdrop1.getRotation() == 180) {
                    accans1.setVisibility(View.GONE);
                    accdrop1.setRotation(0);
                    // Its visible
                    // Toast.makeText(home.this,"Taking Longer Than Usual", Toast.LENGTH_SHORT).show();
                } else {
                    accdrop1.setRotation(180);
                    accans1.setVisibility(View.VISIBLE);
                    // Either gone or invisible
                }

            }
        });
        accdrop1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (accdrop1.getRotation() == 180) {
                    accans1.setVisibility(View.GONE);
                    accdrop1.setRotation(0);
                    // Its visible
                    // Toast.makeText(home.this,"Taking Longer Than Usual", Toast.LENGTH_SHORT).show();
                } else {
                    accdrop1.setRotation(180);
                    accans1.setVisibility(View.VISIBLE);
                    // Either gone or invisible
                }

            }
        });



        accheadques.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (accquesgrid.getVisibility() == View.VISIBLE) {
                    accquesgrid.setVisibility(View.GONE);
                    // Its visible
                    // Toast.makeText(home.this,"Taking Longer Than Usual", Toast.LENGTH_SHORT).show();
                } else {
                    accquesgrid.setVisibility(View.VISIBLE);
                    // Either gone or invisible
                }

                if (warrquesgrid.getVisibility() == View.VISIBLE) {
                    warrquesgrid.setVisibility(View.GONE);

                } if (expquesgrid.getVisibility() == View.VISIBLE){
                    expquesgrid.setVisibility(View.GONE);
                }
            }
        });
        warrheadques.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (warrquesgrid.getVisibility() == View.VISIBLE) {
                    warrquesgrid.setVisibility(View.GONE);
                    // Its visible
                    // Toast.makeText(home.this,"Taking Longer Than Usual", Toast.LENGTH_SHORT).show();
                } else {
                    warrquesgrid.setVisibility(View.VISIBLE);
                    // Either gone or invisible
                }
                if (accquesgrid.getVisibility() == View.VISIBLE) {
                    accquesgrid.setVisibility(View.GONE);

                } if (expquesgrid.getVisibility() == View.VISIBLE){
                    expquesgrid.setVisibility(View.GONE);
                }
            }
        });
        expqueshead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expquesgrid.getVisibility() == View.VISIBLE) {
                    expquesgrid.setVisibility(View.GONE);
                    // Its visible
                    // Toast.makeText(home.this,"Taking Longer Than Usual", Toast.LENGTH_SHORT).show();
                } else {
                    expquesgrid.setVisibility(View.VISIBLE);
                    // Either gone or invisible
                }
                if (warrquesgrid.getVisibility() == View.VISIBLE) {
                    warrquesgrid.setVisibility(View.GONE);

                } if (accquesgrid.getVisibility() == View.VISIBLE){
                    accquesgrid.setVisibility(View.GONE);
                }
            }
        });

        backtn= (ImageView) findViewById(R.id.contactback);
        backtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(contactus.this,home.class);
                startActivity(intent);
            }
        });
    }
}
