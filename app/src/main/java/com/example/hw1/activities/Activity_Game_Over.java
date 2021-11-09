package com.example.hw1.activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.example.hw1.R;

public class Activity_Game_Over extends AppCompatActivity {

    private ImageView panel_IMG_game_over_background;
    private ImageButton panel_BTN_start_over;
    private ImageButton panel_BTN_exit;
    private MediaPlayer theme_music;
    private MediaPlayer exit_music;
    private MediaPlayer start_over_music;
    private int img_player;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);


        Bundle bundle=getIntent().getExtras();
        img_player= bundle.getInt("player");

        findViews();
        InitViews();
        InitBtnClick();

        theme_music = MediaPlayer.create(Activity_Game_Over.this,R.raw.sound_theme_song);
        start_over_music = MediaPlayer.create(Activity_Game_Over.this,R.raw.sound_start_over);
        exit_music = MediaPlayer.create(Activity_Game_Over.this,R.raw.sound_exit);



    }


    public void nextActivity(View v){
        Intent game_page = new Intent(this,Activity_Panel.class);
        game_page.putExtra("player",img_player);
        startActivity(game_page);
    }


    private void InitBtnClick() {

        panel_BTN_start_over.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theme_music.stop();
                start_over_music.start();
                nextActivity(v);
            }
        });

        panel_BTN_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theme_music.stop();
                exit_music.start();
                finishAffinity();
            }
        });
    }

    private void findViews() {
        panel_IMG_game_over_background =findViewById(R.id.panel_IMG_game_over_background);
        panel_BTN_start_over = findViewById(R.id.panel_BTN_start_over);
        panel_BTN_exit = findViewById(R.id.panel_BTN_exit);


    }

    private void InitViews() {

        getImgView(R.drawable.img_game_over_background, panel_IMG_game_over_background);
        getImgView(R.drawable.img_btn_start_over,panel_BTN_start_over);
        getImgView(R.drawable.img_btn_exit,panel_BTN_exit);

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
    }

    @Override
    protected void onStop() {
        theme_music.pause();
        super.onStop();
    }



}