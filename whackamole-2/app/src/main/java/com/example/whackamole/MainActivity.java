package com.example.whackamole;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

LinearLayout layout;
Boolean start = false;
Button starter;
ImageView dv1,dv2,dv3,dv4,dv5,dv6,dv7,dv8,dv9;
TextView timerr,scoree;
private Timer t1,t2;
private TimerTask p,s;
int timercount = 60;
int temp;
int randomizer,randomizer2,randomizer3;
ImageView temper;
int score=0;
ImageView[][] pics = new ImageView[3][3];
ArrayList<ImageView> tally = new ArrayList<ImageView>();
final ScaleAnimation bigger = new ScaleAnimation(1.0f,0.0f,1.0f,0.0f, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
final ScaleAnimation smaller = new ScaleAnimation(0.0f,1.0f,1.0f,1.0f, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bigger.setDuration(1000);
        smaller.setDuration(1000);
        starter = findViewById(R.id.button);
        scoree = findViewById(R.id.score);
        layout = findViewById(R.id.layout);
        timerr = findViewById(R.id.actualtimer);
        dv1 = findViewById(R.id.imageView);
        dv2 = findViewById(R.id.imageView2);
        dv3 = findViewById(R.id.imageView3);
        dv4 = findViewById(R.id.imageView4);
        dv5 = findViewById(R.id.imageView5);
        dv6 = findViewById(R.id.imageView6);
        dv7 = findViewById(R.id.imageView7);
        dv8 = findViewById(R.id.imageView8);
        dv9 = findViewById(R.id.imageView9);
        pics[0][0]=dv1;
        pics[0][1]=dv2;
        pics[0][2]=dv3;
        pics[1][0]=dv4;
        pics[1][1]=dv5;
        pics[1][2]=dv6;
        pics[2][0]=dv7;
        pics[2][1]=dv8;
        pics[2][2]=dv9;

        starter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start=true;
            }
        });


        Timer timer = new Timer();
        p = new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if ((timercount >=0)&&(start==true)) {
                            timerr.setText(timercount + " ");
                            scoree.setText("Score: " +score);
                            decrement(1);
                        }
                        else if(timercount==0){
                            timerr.setText("Game Over!");
                        }

                    }
                });
            }
        };
        timer.schedule(p, 0, 1000);
        t1 = new Timer();
        TimerTask s = new TimerTask() {


            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                       if((timercount >= 0)&&(start==true)) {
                            randomizer = (int) (Math.random() * 3);
                            randomizer2 = (int) (Math.random() * 3);
                            randomizer3 = (int) (Math.random() * 8);
                            if (randomizer3 > 5) {
                                pics[randomizer][randomizer2].setImageResource(R.drawable.head);
                                temp = 2;
                            } else {
                                pics[randomizer][randomizer2].setImageResource(R.drawable.devilfruit);
                                temp = 1;
                            }
//                            pics[randomizer][randomizer2].startAnimation(bigger);
//                            pics[randomizer][randomizer2].setVisibility(View.VISIBLE);

                            pics[randomizer][randomizer2].setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    if (temp == 1) {
                                        try{
                                            increment(5);
                                            Log.d("kk"," "+score);
                                        }catch(Exception e) {

                                        }
                                        pics[randomizer][randomizer2].setOnClickListener(null);
                                        tally.add(pics[randomizer][randomizer2]);

                                        bottom(temp);
                                    } else if (temp == 2) {
                                        tally.add(pics[randomizer][randomizer2]);
                                        pics[randomizer][randomizer2].setOnClickListener(null);
                                        pics[randomizer][randomizer2].setImageResource(R.drawable.damaged);
                                        if(timercount>5) {
                                            decrement(5);
                                        }
                                        else
                                            timercount=0;
                                            bottom(temp);

                                        }
                                }
                            });


                            pics[randomizer][randomizer2].startAnimation(smaller);
                            smaller.setAnimationListener(new Animation.AnimationListener() {
                                @Override
                                public void onAnimationStart(Animation animation) {

                                }

                                @Override
                                public void onAnimationEnd(Animation animation) {
                                    pics[randomizer][randomizer2].startAnimation(bigger);
                                    pics[randomizer][randomizer2].setVisibility(View.INVISIBLE);
                                }

                                @Override
                                public void onAnimationRepeat(Animation animation) {

                                }
                            });



                        }
                   }
                });

            }

        };
        t1.schedule(s, 0, 2000);

    }
    private void bottom(int k){
        LinearLayout.LayoutParams l = new LinearLayout.LayoutParams(40,40);
        ImageView temper = new ImageView(this);
        temper.setId(View.generateViewId());
        if(k==1){
            temper.setImageResource(R.drawable.devilfruit);
        }
        else{
            temper.setImageResource(R.drawable.head);
        }
        temper.setLayoutParams(l);
        layout.addView(temper);
    }
    public synchronized void increment(int h){
        score+=h;
    }
    public synchronized void decrement(int o){
        timercount-=o;
    }
}