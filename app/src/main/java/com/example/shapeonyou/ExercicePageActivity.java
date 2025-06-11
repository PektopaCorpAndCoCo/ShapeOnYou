package com.example.shapeonyou;

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

public class ExercicePageActivity extends AppCompatActivity {

    //private String exerciceType = "abdo";
    private String[] serieOfExercises = {"crunch-abdo", "crunch-bicycle", "planks", "russian-twist", "touche-talon"};
    private ExercicePage[] serieOfExercisesPages;
    private int currentExerciseId = 0; //need to be stored externally to avoid page refresh bug
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

        /*
        // Example usage with dummy data
        ExercicePage exercise = new ExercicePage();
        exercise.name = "Push Ups";
        exercise.videoUrl = "android.resource://" + getPackageName() + "/" + R.raw.test;
        exercise.instructions = "Do 10 push ups slowly and with control.";
        exercise.repetitionNumber = 10;
         */

        //Log.d("DEBUG", "Exercise: " + exercise.name + ", Reps: " + exercise.repetitionNumber);

        // generate all exercises pages
        try {
            serieOfExercisesPages = generateExercicePage(serieOfExercises);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        populateUI(serieOfExercisesPages[currentExerciseId]);

        // Button listeners
        nextButton.setOnClickListener(v -> exerciseCompleted());
        previousButton.setOnClickListener(v -> previousExercise());
        skipButton.setOnClickListener(v -> nextExercise());


    }

    private ExercicePage[] generateExercicePage(String[] serieOfExercises) throws IOException {
        ExercicePage[] result = new ExercicePage[serieOfExercises.length];
        for(int i =0; i< serieOfExercises.length; i++){
            ExercicePage exercicePage = new ExercicePage();
            //exercicePage.name = serieOfExercises[i];

            String name = serieOfExercises[i].replace("-", "_");
            int videoResId = getResources().getIdentifier(name, "raw", getPackageName());
            int csvResId = getResources().getIdentifier(name + "_data", "raw", getPackageName());

            String[] exerciseData = readCsvFile(csvResId);

            exercicePage.name = exerciseData[0];
            exercicePage.videoUrl = "android.resource://" + getPackageName() + "/" + videoResId;
            exercicePage.instructions = exerciseData[1];
            exercicePage.repetitionNumber = 10; //temp hardcode (need to be rethink)

            result[i] = exercicePage;
        }
        return result;
    }

    private String[] readCsvFile(int csvFileId){
        String[] tokens = null;
        try {
            InputStream is = getResources().openRawResource(csvFileId);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;

            if ((line = reader.readLine()) != null) {
                tokens = line.split(",");
            }

            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
            tokens = new String[]{"Name Error", "Instruction Error"};
        }
        return tokens;
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
        if(currentExerciseId == serieOfExercises.length-1){
            //end series
        }else{
            currentExerciseId++;
            populateUI(serieOfExercisesPages[currentExerciseId]);
        }
    }

    private void previousExercise(){
        if(currentExerciseId<=0){
            //return to serie list page
        }else{
            currentExerciseId--;
            populateUI(serieOfExercisesPages[currentExerciseId]);
        }
    }
}
