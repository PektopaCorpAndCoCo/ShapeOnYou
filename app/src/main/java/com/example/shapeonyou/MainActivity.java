package com.example.shapeonyou;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.dashboard);
        //setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.dashboard), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView userIcon = findViewById(R.id.ic_user);
        userIcon.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ProfilActivity.class);
            startActivity(intent);
        });

        ImageView bellIcon = findViewById(R.id.ic_bell);
        bellIcon.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, NotificationActivity.class);
            startActivity(intent);
        });

        ImageView fullImage = findViewById(R.id.hall);
        fullImage.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FullActivity.class);
            startActivity(intent);
        });

        ImageView basImage = findViewById(R.id.down);
        basImage.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DownActivity.class);
            startActivity(intent);
        });

        ImageView UpImage = findViewById(R.id.up);
        UpImage.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, UpActivity.class);
            startActivity(intent);
        });

        ImageView abdosImage = findViewById(R.id.abdos);
        abdosImage.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AbdosActivity.class);
            startActivity(intent);
        });

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nutrition) {
                startActivity(new Intent(MainActivity.this, NutritionActivity.class));
                return true;
            } else if (id == R.id.favoris) {
                startActivity(new Intent(MainActivity.this, FavorisActivity.class));
                return true;
            } else if (id == R.id.progress) {
                startActivity(new Intent(MainActivity.this, SuiviActivity.class));
                return true;
            }

            return false;
        });



    }


}