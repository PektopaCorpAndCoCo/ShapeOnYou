package com.example.shapeonyou;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FullActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fullbody);
        ImageView userIcon = findViewById(R.id.ic_user);
        userIcon.setOnClickListener(v -> {
            Intent intent = new Intent(FullActivity.this, ProfilActivity.class);
            startActivity(intent);
        });

        ImageView bellIcon = findViewById(R.id.ic_bell);
        bellIcon.setOnClickListener(v -> {
            Intent intent = new Intent(FullActivity.this, NotificationActivity.class);
            startActivity(intent);
        });



        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nutrition) {
                startActivity(new Intent(FullActivity.this, NutritionActivity.class));
                return true;
            } else if (id == R.id.favoris) {
                startActivity(new Intent(FullActivity.this, FavorisActivity.class));
                return true;
            } else if (id == R.id.progress) {
                startActivity(new Intent(FullActivity.this, SuiviActivity.class));
                return true;
            }

            return false;
        });
    }


}
