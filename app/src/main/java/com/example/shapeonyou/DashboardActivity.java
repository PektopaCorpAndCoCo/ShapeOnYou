package com.example.shapeonyou;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;


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

        BottomNavigationView navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setSelectedItemId(R.id.home);
        navigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.home) {
                Intent intent = new Intent(DashboardActivity.this, DashboardActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            } else if (id == R.id.nutrition) {
                Intent intent = new Intent(DashboardActivity.this, SelectGoalActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            } else if (id == R.id.profil) {
                Intent intent = new Intent(DashboardActivity.this, ProfileActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            }
            return false;
        });

        ImageView fullImage = findViewById(R.id.all);
        fullImage.setOnClickListener(v -> {
            SerieOfExercisesSingleton.getInstance().setType(ExercisesType.all);
            SerieOfExercisesSingleton.getInstance().setSerieOfExercises(new ArrayList<String>(Arrays.asList("hip-thrust-all","planks-all","pompes-all","rowing-barre-all","squate-all")));
            runSerie();
        });

        ImageView basImage = findViewById(R.id.down);
        basImage.setOnClickListener(v -> {
            SerieOfExercisesSingleton.getInstance().setType(ExercisesType.down);
            SerieOfExercisesSingleton.getInstance().setSerieOfExercises(new ArrayList<String>(Arrays.asList("extension-lombaire-45","fente-avant-halteres","hip-thrust","squate","step-up")));
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