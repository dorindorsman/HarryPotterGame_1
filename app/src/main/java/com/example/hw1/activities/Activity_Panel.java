package com.example.hw1.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hw1.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Activity_Panel extends AppCompatActivity {

    private final int MAX_LIVES = 3;
    private int lives = MAX_LIVES;
    private ImageView[] panel_IMG_hearts;
    private ImageView[] panel_IMG_player;
    private int img_player;

    private int score = 0;
    private TextView panel_LBL_score;

    private int index=0;
    private ArrayList<Img> imgs;
    private ImageView[][] panel_IMG_path;
    private int[][] location;
    private int player_Positon =1;

    private ImageButton panel_BTN_left;
    private ImageButton panel_BTN_right;

    private ImageView panel_IMG_background;
    private boolean setImgDisplay =true;

    private MediaPlayer theme_music;
    private MediaPlayer win_music;
    private MediaPlayer lose_music;
    private Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel);


        imgs =new ArrayList<Img>();
        addImgs();

        Bundle bundle=getIntent().getExtras();
        img_player= bundle.getInt("player");

        findViews();
        InitViews();

        //initialization location variables
        for (int i = 0; i < location.length; i++) {
            for (int j = 0; j < location[i].length; j++) {
                location[i][j]=0;
            }
        }
        InitPlayerMove();
        theme_music = MediaPlayer.create(Activity_Panel.this,R.raw.sound_theme_song);
        win_music = MediaPlayer.create(Activity_Panel.this,R.raw.sound_win);
        lose_music = MediaPlayer.create(Activity_Panel.this,R.raw.sound_lose);

    }



    private void addImgs() {

        imgs.add(new Img().setRes(R.drawable.img_evil).setPrize(false));
        imgs.add(new Img().setRes(R.drawable.img_evil).setPrize(false));
        imgs.add(new Img().setRes(R.drawable.img_prize).setPrize(true));
        imgs.add(new Img().setRes(R.drawable.img_goblet).setPrize(true));
        imgs.add(new Img().setRes(R.drawable.img_ball).setPrize(true));
        imgs.add(new Img().setRes(R.drawable.img_rock).setPrize(true));
        imgs.add(new Img().setRes(R.drawable.img_tree).setPrize(false));
        imgs.add(new Img().setRes(R.drawable.img_hourse).setPrize(false));
        imgs.add(new Img().setRes(R.drawable.img_house).setPrize(false));
        imgs.add(new Img().setRes(R.drawable.img_frog).setPrize(false));
        imgs.add(new Img().setRes(R.drawable.img_goat).setPrize(false));
        imgs.add(new Img().setRes(R.drawable.img_voldermorte).setPrize(false));
        imgs.add(new Img().setRes(R.drawable.img_hat).setPrize(false));

    }


    private void checkCrash() {
            if (location[3][player_Positon]!=0){
                if(imgs.get(location[3][player_Positon]).isPrize()==true){
                   Toast.makeText(this, "Good Job", Toast.LENGTH_SHORT).show();
                    win_music.start();
                   score+=50;
                }else{
                   Toast.makeText(this, "BOOM!", Toast.LENGTH_SHORT).show();
                    lose_music.start();
                   if(score>0){
                      score-=50;
                   }
                   updateLivesViews();
                   vibrate();
                }
                panel_LBL_score.setText("" + score);
            }
    }

    private void updateLivesViews() {
            if (lives>0) {
                panel_IMG_hearts[--lives].setVisibility(View.INVISIBLE);
            } else {
                Intent game_over_page = new Intent(this,Activity_Game_Over.class);
                game_over_page.putExtra("player",img_player);
                theme_music.stop();
                startActivity(game_over_page);

            }
    }



    private void runLogic() {

        for (int i = location.length-1; i >0 ; i--) {
            for (int j = location[0].length-1; j>-1 ; j--) {
                location[i][j]=location[i-1][j];
            }
        }
        location[0][0]=0;
        location[0][1]=0;
        location[0][2]=0;
        index=(int) (Math.random()*(imgs.size()-1)+1);
        if (index== 0){
            index++;
        }
        if(setImgDisplay){
            location[0][(int) (Math.random()*3)]=index;
            setImgDisplay =false;
        }else {
            setImgDisplay =true;
        }
        updateUI();
        checkCrash();
    }

    private void updateUI() {

        for (int i = 0; i < panel_IMG_path.length; i++) {
            for (int j = 0; j < panel_IMG_path[i].length; j++) {
                ImageView im= panel_IMG_path[i][j];
                switch (location[i][j]){
                    case 0:
                        im.setVisibility(View.INVISIBLE);
                        break;
                    case 1:
                        im.setVisibility(View.VISIBLE);
                        im.setImageResource(imgs.get(1).getRes());
                        break;

                    case 2:
                        im.setVisibility(View.VISIBLE);
                        im.setImageResource(imgs.get(2).getRes());
                        break;

                    case 3:
                        im.setVisibility(View.VISIBLE);
                        im.setImageResource(imgs.get(3).getRes());
                        break;

                    case 4:
                        im.setVisibility(View.VISIBLE);
                        im.setImageResource(imgs.get(4).getRes());
                        break;

                    case 5:
                        im.setVisibility(View.VISIBLE);
                        im.setImageResource(imgs.get(5).getRes());
                        break;

                    case 6:
                        im.setVisibility(View.VISIBLE);
                        im.setImageResource(imgs.get(6).getRes());
                        break;

                    case 7:
                        im.setVisibility(View.VISIBLE);
                        im.setImageResource(imgs.get(7).getRes());
                        break;

                    case 8:
                        im.setVisibility(View.VISIBLE);
                        im.setImageResource(imgs.get(8).getRes());
                        break;

                    case 9:
                        im.setVisibility(View.VISIBLE);
                        im.setImageResource(imgs.get(9).getRes());
                        break;

                    case 10:
                        im.setVisibility(View.VISIBLE);
                        im.setImageResource(imgs.get(10).getRes());
                        break;

                    case 11:
                        im.setVisibility(View.VISIBLE);
                        im.setImageResource(imgs.get(11).getRes());
                        break;

                    case 12:
                        im.setVisibility(View.VISIBLE);
                        im.setImageResource(imgs.get(12).getRes());
                        break;

                    case 13:
                        im.setVisibility(View.VISIBLE);
                        im.setImageResource(imgs.get(13).getRes());
                        break;

                }
            }
        }
    }

    private void findViews() {

        panel_IMG_background =findViewById(R.id.panel_IMG_background);
        panel_LBL_score = findViewById(R.id.panel_LBL_score);
        panel_IMG_hearts = new ImageView[] {
                findViewById(R.id.panel_IMG_heart1),
                findViewById(R.id.panel_IMG_heart2),
                findViewById(R.id.panel_IMG_heart3)
        };

        panel_IMG_path= new ImageView[][]{
                {findViewById(R.id.panel_IMG_00),findViewById(R.id.panel_IMG_01),findViewById(R.id.panel_IMG_02)},
                {findViewById(R.id.panel_IMG_10),findViewById(R.id.panel_IMG_11),findViewById(R.id.panel_IMG_12)},
                {findViewById(R.id.panel_IMG_20),findViewById(R.id.panel_IMG_21),findViewById(R.id.panel_IMG_22)},
                {findViewById(R.id.panel_IMG_30),findViewById(R.id.panel_IMG_31),findViewById(R.id.panel_IMG_32)},
        };


        panel_BTN_left = findViewById(R.id.panel_BTN_left);
        panel_BTN_right= findViewById(R.id.panel_BTN_right);

        panel_IMG_player=new ImageView[]{findViewById(R.id.panel_IMG_player1),findViewById(R.id.panel_IMG_player2),findViewById(R.id.panel_IMG_player3)};



        location=new int[panel_IMG_path.length][panel_IMG_path[0].length];

    }

    private void InitViews() {

        getImgView(R.drawable.img_game_background, panel_IMG_background);
        for (int i = 0; i < panel_IMG_hearts.length; i++) {
            getImgView(R.drawable.img_heart,panel_IMG_hearts[i]);

        }


        for (int i = 0; i < panel_IMG_player.length; i++) {
            getImgView(img_player,panel_IMG_player[i]);
        }

        getImgView(R.drawable.img_btn_left,panel_BTN_left);
        getImgView(R.drawable.img_btn_right,panel_BTN_right);

    }

    private void getImgView(int img_src, ImageView img_view) {
        Glide
                .with(this)
                .load(img_src)
                .into(img_view);

    }

    protected void onStart() {
        super.onStart();
        theme_music.start();
        theme_music.setLooping(true);
        startTicker();
    }

    @Override
    protected void onStop() {
        theme_music.pause();
        super.onStop();
        stopTicker();
    }

    private void startTicker() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        runLogic();
                    }
                });
            }
        }, 0, 1000);
    }



    private void stopTicker() {
        timer.cancel();
    }



    private void InitPlayerMove() {

        panel_BTN_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player_Positon !=0){
                    panel_IMG_player[player_Positon].setVisibility(View.INVISIBLE);
                    panel_IMG_player[player_Positon -1].setVisibility(View.VISIBLE);
                    player_Positon--;
                }

            }
        });

        panel_BTN_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player_Positon !=2){
                    panel_IMG_player[player_Positon].setVisibility(View.INVISIBLE);
                    panel_IMG_player[player_Positon +1].setVisibility(View.VISIBLE);
                    player_Positon++;
                }
            }
        });

    }


    private void vibrate() {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            v.vibrate(500);
        }
    }


}