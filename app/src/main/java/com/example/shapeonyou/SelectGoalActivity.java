// SelectGoalActivity.java
package com.example.shapeonyou;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class SelectGoalActivity extends AppCompatActivity {

    public static final String EXTRA_GOAL = "com.example.shapeonyou.EXTRA_GOAL";
    public static final String GOAL_MASSE = "prise_de_masse";
    public static final String GOAL_SECHE = "seche";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_goal);

        CardView cardMass = findViewById(R.id.card_mass);
        CardView cardCut  = findViewById(R.id.card_cut);

        cardMass.setOnClickListener(v -> {
            Intent intent = new Intent(SelectGoalActivity.this, NutritionAdviceActivity.class);
            intent.putExtra(EXTRA_GOAL, GOAL_MASSE);
            startActivity(intent);
        });

        cardCut.setOnClickListener(v -> {
            Intent intent = new Intent(SelectGoalActivity.this, NutritionAdviceActivity.class);
            intent.putExtra(EXTRA_GOAL, GOAL_SECHE);
            startActivity(intent);
        });
    }
}
