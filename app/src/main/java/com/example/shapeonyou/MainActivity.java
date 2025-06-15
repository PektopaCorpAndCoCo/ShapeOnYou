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



    }


}