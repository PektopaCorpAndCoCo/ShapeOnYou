package com.example.shapeonyou;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AbdosActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.abdos);
        ImageView userIcon = findViewById(R.id.ic_user);
        userIcon.setOnClickListener(v -> {
            Intent intent = new Intent(AbdosActivity.this, ProfilActivity.class);
            startActivity(intent);
        });

        ImageView bellIcon = findViewById(R.id.ic_bell);
        bellIcon.setOnClickListener(v -> {
            Intent intent = new Intent(AbdosActivity.this, NotificationActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btn_retour).setOnClickListener(v -> {
            Intent intent = new Intent(AbdosActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // facultatif : pour éviter de revenir à FullActivity avec le bouton back
            startActivity(intent);
        });




        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nutrition) {
                startActivity(new Intent(AbdosActivity.this, NutritionActivity.class));
                return true;
            } else if (id == R.id.favoris) {
                startActivity(new Intent(AbdosActivity.this, FavorisActivity.class));
                return true;
            } else if (id == R.id.progress) {
                startActivity(new Intent(AbdosActivity.this, SuiviActivity.class));
                return true;
            }

            return false;
        });
    }


}
