package com.example.hw1.activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.example.hw1.R;

import java.util.ArrayList;

public class Activity_Menu extends AppCompatActivity {

    private ImageView panel_IMG_game_menu_background;
    private TextView panel_LBL_start;
    private TextView panel_LBL_tap;
    private ImageButton panel_BTN_start;
    private ImageButton panel_BTN_setting;
    private ImageButton panel_BTN_player;
    private ImageButton panel_BTN_choose;
    private MediaPlayer theme_music;
    private int index=0;
    private ArrayList<Img> imgsPlayer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        imgsPlayer =new ArrayList<Img>();
        addImgsPlayer();


        findViews();
        InitViews();
        InitBtnClick();
        theme_music= MediaPlayer.create(Activity_Menu.this,R.raw.sound_theme_song);

    }

    private void addImgsPlayer() {
        imgsPlayer.add(new Img().setRes(R.drawable.img_harrypoter));
        imgsPlayer.add(new Img().setRes(R.drawable.img_hermione));
        imgsPlayer.add(new Img().setRes(R.drawable.img_malfoy));
        imgsPlayer.add(new Img().setRes(R.drawable.img_ron));
        imgsPlayer.add(new Img().setRes(R.drawable.img_hagrid));
        imgsPlayer.add(new Img().setRes(R.drawable.img_mcgonagall));
        imgsPlayer.add(new Img().setRes(R.drawable.img_snape));

    }

    private void setImgPlayer() {
            panel_BTN_start.setVisibility(View.INVISIBLE);
            panel_LBL_start.setVisibility(View.INVISIBLE);
            panel_BTN_setting.setVisibility(View.INVISIBLE);
            panel_BTN_player.setVisibility(View.VISIBLE);
            panel_BTN_choose.setVisibility(View.VISIBLE);
            panel_LBL_tap.setVisibility(View.VISIBLE);

    }

    public void nextActivity(View v){
        Intent game_page = new Intent(this,Activity_Panel.class);
        game_page.putExtra("player",imgsPlayer.get(index).getRes());
        startActivity(game_page);

    }


    private void InitBtnClick() {

        panel_BTN_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theme_music.stop();
                nextActivity(v);
            }
        });

        panel_BTN_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setImgPlayer();
            }
        });


        panel_BTN_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index++;
                if(index==imgsPlayer.size()){
                    index=0;
                }
                panel_BTN_player.setImageResource(imgsPlayer.get(index).getRes());
            }
        });

        panel_BTN_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToMenu();
            }
        });
    }


    private void backToMenu() {
        panel_BTN_start.setVisibility(View.VISIBLE);
        panel_LBL_start.setVisibility(View.VISIBLE);
        panel_BTN_setting.setVisibility(View.VISIBLE);
        panel_BTN_player.setVisibility(View.INVISIBLE);
        panel_BTN_choose.setVisibility(View.INVISIBLE);
        panel_LBL_tap.setVisibility(View.INVISIBLE);
    }

    private void findViews() {
        panel_IMG_game_menu_background =findViewById(R.id.panel_IMG_game_menu_background);
        panel_BTN_start = findViewById(R.id.panel_BTN_start);
        panel_BTN_setting = findViewById(R.id.panel_BTN_setting);
        panel_LBL_start = findViewById(R.id.panel_LBL_start);

        panel_LBL_tap = findViewById(R.id.panel_LBL_tap);
        panel_BTN_player = findViewById(R.id.panel_BTN_player);
        panel_BTN_choose = findViewById(R.id.panel_BTN_choose);

        }

    private void InitViews() {

        getImgView(R.drawable.img_menu_background, panel_IMG_game_menu_background);
        getImgView(R.drawable.img_btn_start,panel_BTN_start);
        getImgView(R.drawable.img_btn_feather,panel_BTN_setting);
        getImgView(R.drawable.img_harrypoter,panel_BTN_player);
        getImgView(R.drawable.img_btn_feather,panel_BTN_choose);

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



