package com.example.shapeonyou;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Quest1Activity extends AppCompatActivity {

    private EditText etSexe, etAge, etTaille, etPoids;
    private Button btnSignUp;

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest1);

        // Liaison des vues
        etSexe    = findViewById(R.id.etSexe);
        etAge     = findViewById(R.id.etAge);
        etTaille  = findViewById(R.id.etTaille);
        etPoids   = findViewById(R.id.etPoids);
        btnSignUp = findViewById(R.id.btnSignUp);

        mAuth = FirebaseAuth.getInstance();
        db    = FirebaseFirestore.getInstance();

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveProfile();
            }
        });
    }

    private void saveProfile() {
        btnSignUp.setEnabled(false);

        String sexe     = etSexe.getText().toString().trim();
        String ageStr   = etAge.getText().toString().trim();
        String tailleStr = etTaille.getText().toString().trim();
        String poidsStr = etPoids.getText().toString().trim();

        if (TextUtils.isEmpty(sexe) ||
                TextUtils.isEmpty(ageStr) ||
                TextUtils.isEmpty(tailleStr) ||
                TextUtils.isEmpty(poidsStr)) {
            Toast.makeText(this, "Merci de remplir tous les champs", Toast.LENGTH_SHORT).show();
            btnSignUp.setEnabled(true);
            return;
        }

        int age = Integer.parseInt(ageStr);
        double taille = Double.parseDouble(tailleStr.replace(',', '.'));
        double poids  = Double.parseDouble(poidsStr.replace(',', '.'));

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            Toast.makeText(this, "Utilisateur non authentifié", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        String uid = currentUser.getUid();

        Map<String, Object> profile = new HashMap<>();
        profile.put("sexe", sexe);
        profile.put("age", age);
        profile.put("taille", taille);
        profile.put("poids", poids);

        db.collection("users")
                .document(uid)
                .set(profile, com.google.firebase.firestore.SetOptions.merge())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(Quest1Activity.this, "Profil enregistré", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Quest1Activity.this, Quest2Activity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Quest1Activity.this,
                                "Erreur lors de l'enregistrement : " + e.getMessage(),
                                Toast.LENGTH_LONG).show();
                        btnSignUp.setEnabled(true);
                    }
                });
    }
}