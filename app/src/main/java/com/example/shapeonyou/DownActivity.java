package com.example.shapeonyou;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DownActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.abdos);
        ImageView userIcon = findViewById(R.id.ic_user);
        userIcon.setOnClickListener(v -> {
            Intent intent = new Intent(DownActivity.this, ProfilActivity.class);
            startActivity(intent);
        });

        ImageView bellIcon = findViewById(R.id.ic_bell);
        bellIcon.setOnClickListener(v -> {
            Intent intent = new Intent(DownActivity.this, NotificationActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btn_retour).setOnClickListener(v -> {
            Intent intent = new Intent(DownActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // facultatif : pour éviter de revenir à FullActivity avec le bouton back
            startActivity(intent);
        });




        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nutrition) {
                startActivity(new Intent(DownActivity.this, NutritionActivity.class));
                return true;
            } else if (id == R.id.favoris) {
                startActivity(new Intent(DownActivity.this, FavorisActivity.class));
                return true;
            } else if (id == R.id.progress) {
                startActivity(new Intent(DownActivity.this, SuiviActivity.class));
                return true;
            }

            return false;
        });
    }


}
