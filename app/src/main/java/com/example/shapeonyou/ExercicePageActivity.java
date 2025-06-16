package com.example.shapeonyou;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ExercicePageActivity extends AppCompatActivity {

    //private String exerciceType = "abdo";
    //private String[] serieOfExercises = {"crunch-abdo", "crunch-bicycle", "planks", "russian-twist", "touche-talon"};
    //private final ArrayList<String> serieOfExercises = SerieOfExercisesSingleton.getInstance().getSerieOfExercises();
    //private ExercicePage[] serieOfExercisesPages;
    //private int currentExerciseId = 0; //need to be stored externally to avoid page refresh bug
    private VideoView videoView;
    private TextView titleTextView;
    private TextView repetitionsTextView;
    private TextView instructionsTextView;
    private Button previousButton, skipButton, nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercice_page);

        // Link XML views to Java variables
        videoView = findViewById(R.id.videoView);
        titleTextView = findViewById(R.id.title);
        repetitionsTextView = findViewById(R.id.nbRep);
        instructionsTextView = findViewById(R.id.instructions);

        previousButton = findViewById(R.id.previousButton);
        skipButton = findViewById(R.id.skipButton);
        nextButton = findViewById(R.id.nextButton);



        populateUI(SerieOfExercisesSingleton.getInstance().getCurrentExercicePage());

        // Button listeners
        nextButton.setOnClickListener(v -> exerciseCompleted());
        previousButton.setOnClickListener(v -> previousExercise());
        skipButton.setOnClickListener(v -> nextExercise());


    }

    private void populateUI(ExercicePage exercise) {
        //Log.d("DEBUG", "Populating UI with: " + exercise.name);
        titleTextView.setText(exercise.name);
        repetitionsTextView.setText(String.valueOf(exercise.repetitionNumber));
        instructionsTextView.setText(exercise.instructions);

        Uri videoUri = Uri.parse(exercise.videoUrl);
        videoView.setVideoURI(videoUri);
        videoView.setOnCompletionListener(mp -> videoView.start());
        videoView.start();
    }

    private void exerciseCompleted(){
        //send data for personalized training
        nextExercise();
    }

    private void nextExercise(){
        if(SerieOfExercisesSingleton.getInstance().nextExercise()){
            populateUI(SerieOfExercisesSingleton.getInstance().getCurrentExercicePage());
        }else{
            //end series
            //go to dashboard/win panel
            SerieOfExercisesSingleton.getInstance().reset();
            Intent intent = new Intent(ExercicePageActivity.this, DashboardActivity.class);
            startActivity(intent);
        }
    }

    private void previousExercise(){
        if(SerieOfExercisesSingleton.getInstance().previousExercise()){
            populateUI(SerieOfExercisesSingleton.getInstance().getCurrentExercicePage());
        }else{
            //return to serie list page
            Intent intent = new Intent(ExercicePageActivity.this, BodyPartActivity.class);
            startActivity(intent);
        }
    }
}
