package com.example.shapeonyou;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.dashboard);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.dashboard), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView fullImage = findViewById(R.id.all);
        fullImage.setOnClickListener(v -> {
            SerieOfExercisesSingleton.getInstance().setType(ExercisesType.all);
            SerieOfExercisesSingleton.getInstance().setSerieOfExercises(new ArrayList<String>(Arrays.asList("")));
            runSerie();
        });

        ImageView basImage = findViewById(R.id.down);
        basImage.setOnClickListener(v -> {
            SerieOfExercisesSingleton.getInstance().setType(ExercisesType.down);
            SerieOfExercisesSingleton.getInstance().setSerieOfExercises(new ArrayList<String>(Arrays.asList("")));
            runSerie();
        });

        ImageView UpImage = findViewById(R.id.up);
        UpImage.setOnClickListener(v -> {
            SerieOfExercisesSingleton.getInstance().setType(ExercisesType.up);
            SerieOfExercisesSingleton.getInstance().setSerieOfExercises(new ArrayList<String>(Arrays.asList("curl-concentre", "dips-avec-une-chaise", "face-pull", "pompes", "rowing-barre")));
            runSerie();
        });

        ImageView abdosImage = findViewById(R.id.abdos);
        abdosImage.setOnClickListener(v -> {
            SerieOfExercisesSingleton.getInstance().setType(ExercisesType.abdo);
            SerieOfExercisesSingleton.getInstance().setSerieOfExercises(new ArrayList<String>(Arrays.asList("crunch-abdo", "crunch-bicycle", "planks", "russian-twist", "touche-talon")));
            runSerie();
        });
    }

    private void runSerie(){
        Intent intent = new Intent(DashboardActivity.this, BodyPartActivity.class);
        startActivity(intent);
    }
}