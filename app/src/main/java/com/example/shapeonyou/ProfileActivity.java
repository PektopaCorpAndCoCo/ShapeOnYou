package com.example.shapeonyou;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProfileActivity extends AppCompatActivity {

    private TextView tvEmail, tvAge, tvTaille, tvPoids, tvSexe, tvLevel, tvFreq;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvEmail = findViewById(R.id.tvEmail);
        tvAge   = findViewById(R.id.tvAge);
        tvTaille = findViewById(R.id.tvTaille);
        tvPoids = findViewById(R.id.tvPoids);
        tvSexe  = findViewById(R.id.tvSexe);
        tvLevel = findViewById(R.id.tvLevel);
        tvFreq  = findViewById(R.id.tvFrequency);

        setupBottomNavigation();
        loadProfile();
    }

    private void setupBottomNavigation() {
        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setSelectedItemId(R.id.profil);
        bottomNavigation.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.home) {
                Intent intent = new Intent(ProfileActivity.this, DashboardActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            } else if (id == R.id.nutrition) {
                Intent intent = new Intent(ProfileActivity.this, SelectGoalActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            } else if (id == R.id.profil) {
                Intent intent = new Intent(ProfileActivity.this, ProfileActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            }
            return false;
        });
    }

    private void loadProfile() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            Toast.makeText(this, "Utilisateur non connect\u00e9", Toast.LENGTH_SHORT).show();
            return;
        }

        tvEmail.setText(user.getEmail());

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users")
                .document(user.getUid())
                .get()
                .addOnSuccessListener(snapshot -> fillFromSnapshot(snapshot))
                .addOnFailureListener(e -> Toast.makeText(ProfileActivity.this,
                        "Erreur de chargement", Toast.LENGTH_SHORT).show());
    }

    private void fillFromSnapshot(DocumentSnapshot snap) {
        if (snap == null || !snap.exists()) {
            return;
        }
        Object age = snap.get("age");
        Object taille = snap.get("taille");
        Object poids = snap.get("poids");
        Object sexe = snap.get("sexe");
        Object level = snap.get("level");
        Object freq = snap.get("frequencyPerWeek");

        if (age != null) tvAge.setText(String.valueOf(age));
        if (taille != null) tvTaille.setText(String.valueOf(taille));
        if (poids != null) tvPoids.setText(String.valueOf(poids));
        if (sexe != null) tvSexe.setText(String.valueOf(sexe));
        if (level != null) tvLevel.setText(String.valueOf(level));
        if (freq != null) tvFreq.setText(String.valueOf(freq));
    }
}