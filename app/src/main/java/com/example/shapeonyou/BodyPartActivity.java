package com.example.shapeonyou;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;

public class BodyPartActivity extends AppCompatActivity {

    private ExercisesType type;
/*
    private VideoView videoView1;
    private VideoView videoView2;
    private VideoView videoView3;
    private VideoView videoView4;
    private VideoView videoView5;

    private TextView titleNameTextView1;
    private TextView titleNameTextView2;
    private TextView titleNameTextView3;
    private TextView titleNameTextView4;
    private TextView titleNameTextView5;*/

    private ArrayList<VideoView> videoViewList = new ArrayList<VideoView>();
    private ArrayList<TextView> textViewList = new ArrayList<TextView>();


    private Button backButton, startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.serie_list);




        videoViewList.add(findViewById(R.id.videoView1));
        videoViewList.add(findViewById(R.id.videoView2));
        videoViewList.add(findViewById(R.id.videoView3));
        videoViewList.add(findViewById(R.id.videoView4));
        videoViewList.add(findViewById(R.id.videoView5));


        textViewList.add(findViewById(R.id.title_text_view1));
        textViewList.add(findViewById(R.id.title_text_view2));
        textViewList.add(findViewById(R.id.title_text_view3));
        textViewList.add(findViewById(R.id.title_text_view4));
        textViewList.add(findViewById(R.id.title_text_view5));




        backButton = findViewById(R.id.btn_retour);
        startButton = findViewById(R.id.btn_demarrer);


        backButton.setOnClickListener(v -> back());

        startButton.setOnClickListener(v -> start());

        /*
        findViewById(R.id.btn_retour).setOnClickListener(v -> {
            Intent intent = new Intent(BodyPartActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // facultatif : pour éviter de revenir à FullActivity avec le bouton back
            startActivity(intent);
        });*/


        populateUI(SerieOfExercisesSingleton.getInstance().getSerieOfExercisesPages());




    }

    private void populateUI(ArrayList<ExercicePage> exercise) {
        for(int i =0; i<5; i++){
            //VideoView videoViewTemp = videoViewList.get(i);
            Uri videoUri = Uri.parse(exercise.get(i).videoUrl);
            videoViewList.get(i).setVideoURI(videoUri);
            int finalI = i;
            videoViewList.get(i).setOnCompletionListener(mp -> videoViewList.get(finalI).start());
            videoViewList.get(i).start();

            textViewList.get(i).setText(exercise.get(i).name);

        }/*
        for(int i =0; i<5; i++) {
            textViewList.get(i).setText(exercise.get(i).name);
        }

        Uri videoUri = Uri.parse(exercise.get(0).videoUrl);
        videoView1.setVideoURI(videoUri);
        videoView1.setOnCompletionListener(mp -> videoView1.start());
        videoView1.start();*/

        /*Uri videoUri2 = Uri.parse(exercise.get(1).videoUrl);
        videoView2.setVideoURI(videoUri);
        videoView2.setOnCompletionListener(mp -> videoView2.start());
        videoView2.start();*/
    }

    private void back(){
        //retour à la page précédente
    }

    private void start(){
        //démarrer série
    }



    }
