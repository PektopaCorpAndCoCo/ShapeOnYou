package com.example.shapeonyou;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;

public class ExercicePageActivity extends AppCompatActivity {

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

        //Log.d("DEBUG", "Activity started");
        // Example usage with dummy data
        ExercicePage exercise = new ExercicePage();
        exercise.name = "Push Ups";
        exercise.videoUrl = "android.resource://" + getPackageName() + "/" + R.raw.test;
        exercise.instructions = "Do 10 push ups slowly and with control.";
        exercise.repetitionNumber = 10;

        //Log.d("DEBUG", "Exercise: " + exercise.name + ", Reps: " + exercise.repetitionNumber);
        populateUI(exercise);
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
}
