// SelectGoalActivity.java
package com.example.shapeonyou;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SelectGoalActivity extends AppCompatActivity {

    public static final String EXTRA_GOAL = "com.example.shapeonyou.EXTRA_GOAL";
    public static final String GOAL_MASSE = "prise_de_masse";
    public static final String GOAL_SECHE = "seche";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_goal);

        Button btnMasse = findViewById(R.id.btnPriseDeMasse);
        Button btnSeche = findViewById(R.id.btnSeche);

        btnMasse.setOnClickListener(v -> {
            Intent intent = new Intent(SelectGoalActivity.this, NutritionAdviceActivity.class);
            intent.putExtra(EXTRA_GOAL, GOAL_MASSE);
            startActivity(intent);
        });

        btnSeche.setOnClickListener(v -> {
            Intent intent = new Intent(SelectGoalActivity.this, NutritionAdviceActivity.class);
            intent.putExtra(EXTRA_GOAL, GOAL_SECHE);
            startActivity(intent);
        });
    }
}
