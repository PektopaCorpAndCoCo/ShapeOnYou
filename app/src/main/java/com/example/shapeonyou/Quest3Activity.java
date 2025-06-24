package com.example.shapeonyou;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class Quest3Activity extends AppCompatActivity {

    private Button btnOptionOneToTwo, btnOptionThreeToFour, btnOptionFivePlus;
    private Button btnContinue3;
    private String selectedFrequency = null;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest3);

        // 1) Bind views
        btnOptionOneToTwo    = findViewById(R.id.btnOptionOneToTwo);
        btnOptionThreeToFour = findViewById(R.id.btnOptionThreeToFour);
        btnOptionFivePlus    = findViewById(R.id.btnOptionFivePlus);
        btnContinue3         = findViewById(R.id.btnContinue3);

        // 2) Init Firebase
        mAuth = FirebaseAuth.getInstance();
        db    = FirebaseFirestore.getInstance();

        // 3) Listeners pour les options
        View.OnClickListener optionClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetOptionStates();
                animateSelection(v);
                v.setSelected(true);
                selectedFrequency = ((Button)v).getText().toString();
            }
        };
        btnOptionOneToTwo.setOnClickListener(optionClick);
        btnOptionThreeToFour.setOnClickListener(optionClick);
        btnOptionFivePlus.setOnClickListener(optionClick);

        // 4) Listener pour Continuer
        btnContinue3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveFrequency();
            }
        });
    }

    private void resetOptionStates() {
        Button[] buttons = {
                btnOptionOneToTwo,
                btnOptionThreeToFour,
                btnOptionFivePlus
        };
        for (Button b : buttons) {
            b.setSelected(false);
            b.setScaleX(1f);
            b.setScaleY(1f);
        }
    }

    private void animateSelection(View selected) {
        selected.animate()
                .scaleX(1.1f)
                .scaleY(1.1f)
                .setDuration(150)
                .setInterpolator(new DecelerateInterpolator())
                .start();
    }

    private void saveFrequency() {
        if (selectedFrequency == null) {
            Toast.makeText(this, "Merci de choisir une fréquence", Toast.LENGTH_SHORT).show();
            return;
        }
        btnContinue3.setEnabled(false);

        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            Toast.makeText(this, "Utilisateur non authentifié", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        String uid = user.getUid();
        db.collection("users")
                .document(uid)
                .update("frequencyPerWeek", selectedFrequency)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(Quest3Activity.this, "Fréquence enregistrée", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Quest3Activity.this, LoginActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Quest3Activity.this,
                                "Erreur : " + e.getMessage(),
                                Toast.LENGTH_LONG).show();
                        btnContinue3.setEnabled(true);
                    }
                });
    }
}
