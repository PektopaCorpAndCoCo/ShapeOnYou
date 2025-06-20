package com.example.shapeonyou;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

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

        TextView tvMass = findViewById(R.id.card_mass);
        TextView tvCut  = findViewById(R.id.card_cut);

        tvMass.setOnClickListener(v -> {
            Intent i = new Intent(this, NutritionAdviceActivity.class);
            i.putExtra(EXTRA_GOAL, GOAL_MASSE);
            startActivity(i);
        });
        tvCut.setOnClickListener(v -> {
            Intent i = new Intent(this, NutritionAdviceActivity.class);
            i.putExtra(EXTRA_GOAL, GOAL_SECHE);
            startActivity(i);
        });

        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setSelectedItemId(R.id.nutrition);
        bottomNavigation.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nutrition) {
                Intent intent = new Intent(SelectGoalActivity.this, SelectGoalActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            }
            return false;
        });
    }
}
