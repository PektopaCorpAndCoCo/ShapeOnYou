package com.example.shapeonyou;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 1. Init Firebase
        FirebaseApp.initializeApp(this);

        // 2. Edge-to-edge (status & nav bars)
        EdgeToEdge.enable(this);

        // 3. Charge le vrai layout principal
        setContentView(R.layout.activity_main);

        // 4. Applique les insets système (status/nav bars) à la racine
        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(android.R.id.content),
                (view, insets) -> {
                    Insets sys = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                    view.setPadding(sys.left, sys.top, sys.right, sys.bottom);
                    return insets;
                }
        );
    }
}
