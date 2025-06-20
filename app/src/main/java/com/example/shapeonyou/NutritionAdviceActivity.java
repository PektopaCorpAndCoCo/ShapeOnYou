// NutritionAdviceActivity.java
package com.example.shapeonyou;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import android.content.Intent;
import com.example.shapeonyou.DashboardActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Activity qui affiche la liste de conseils en fonction de l'objectif passé en EXTRA.
 * Attend un EXTRA : SelectGoalActivity.EXTRA_GOAL (valeur "prise_de_masse" ou "seche").
 */
public class NutritionAdviceActivity extends AppCompatActivity {

    private RecyclerView rvAdviceList;
    private AdviceAdapter adapter;
    private final List<Advice> adviceList = new ArrayList<>();

    private String goalKey; // "prise_de_masse" ou "seche"

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_advice);

        rvAdviceList = findViewById(R.id.rvAdviceList);
        rvAdviceList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AdviceAdapter(adviceList);
        rvAdviceList.setAdapter(adapter);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nutrition);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home) {
                startActivity(new Intent(this, DashboardActivity.class));
                return true;
            }
            return item.getItemId() == R.id.nutrition;
        });

        // Récupère l'extra depuis l'Intent
        if (getIntent() != null && getIntent().hasExtra(SelectGoalActivity.EXTRA_GOAL)) {
            goalKey = getIntent().getStringExtra(SelectGoalActivity.EXTRA_GOAL);
        } else {
            // Si on n'a pas d'objectif, erreur ou retour
            Toast.makeText(this, "Objectif non spécifié !", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Charge depuis le JSON local
        loadAdvicesFromJson(goalKey);
    }

    /**
     * Charge la liste de conseils (prise_de_masse OU seche) depuis le fichier JSON situé dans assets/advices.json
     * @param keyGoal : "prise_de_masse" ou "seche"
     */
    private void loadAdvicesFromJson(String keyGoal) {
        try {
            InputStream is = getAssets().open("advices.json");
            InputStreamReader reader = new InputStreamReader(is);

            // On lit le JSON entier dans un JsonObject
            Gson gson = new Gson();
            JsonObject root = gson.fromJson(reader, JsonObject.class);

            if (!root.has(keyGoal)) {
                Toast.makeText(this, "Aucun conseil pour cet objectif.", Toast.LENGTH_SHORT).show();
                reader.close();
                return;
            }

            // On convertit la sous-liste correspondant à keyGoal en List<Advice>
            Type listType = new TypeToken<List<Advice>>() {}.getType();
            List<Advice> loaded = gson.fromJson(root.get(keyGoal), listType);

            adviceList.clear();
            adviceList.addAll(loaded);
            adapter.notifyDataSetChanged();

            reader.close();
        } catch (Exception e) {
            Log.e("NutritionAdvice", "Erreur lecture JSON", e);
            Toast.makeText(this, "Impossible de charger les conseils", Toast.LENGTH_SHORT).show();
        }
    }
}
