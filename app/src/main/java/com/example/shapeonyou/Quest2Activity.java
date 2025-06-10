package com.example.shapeonyou;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class Quest2Activity extends AppCompatActivity {

    private Button btnOptionBeginner, btnOptionIntermediate, btnOptionAdvanced;
    private Button btnContinue;

    private String selectedLevel = null;

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest2);

        // 1) bind views
        btnOptionBeginner     = findViewById(R.id.btnOptionBeginner);
        btnOptionIntermediate = findViewById(R.id.btnOptionIntermediate);
        btnOptionAdvanced     = findViewById(R.id.btnOptionAdvanced);
        btnContinue           = findViewById(R.id.btnContinue);

        // 2) init Firebase
        mAuth = FirebaseAuth.getInstance();
        db    = FirebaseFirestore.getInstance();

        // 3) setup option listeners
        View.OnClickListener optionClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // reset tous les boutons à l’état normal
                resetOptionStyles();
                // cocher celui qu’on a cliqué
                v.setSelected(true);
                selectedLevel = ((Button)v).getText().toString();
            }
        };
        btnOptionBeginner.setOnClickListener(optionClick);
        btnOptionIntermediate.setOnClickListener(optionClick);
        btnOptionAdvanced.setOnClickListener(optionClick);

        // 4) bouton Continuer
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveLevel();
            }
        });
    }

    private void resetOptionStyles() {
        // on désélectionne visuellement tous les boutons
        btnOptionBeginner.setSelected(false);
        btnOptionIntermediate.setSelected(false);
        btnOptionAdvanced.setSelected(false);
    }

    private void saveLevel() {
        if (selectedLevel == null) {
            Toast.makeText(this, "Merci de choisir un niveau", Toast.LENGTH_SHORT).show();
            return;
        }

        btnContinue.setEnabled(false);

        // récupère l'utilisateur courant
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            Toast.makeText(this, "Utilisateur non authentifié", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        String uid = currentUser.getUid();

        // écrit uniquement le champ "level" dans le doc user
        db.collection("users")
                .document(uid)
                .update("level", selectedLevel)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(Quest2Activity.this, "Niveau enregistré", Toast.LENGTH_SHORT).show();
                        // passe à l'activité suivante
                        Intent i = new Intent(Quest2Activity.this, MainActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Quest2Activity.this,
                                "Erreur : " + e.getMessage(),
                                Toast.LENGTH_LONG).show();
                        btnContinue.setEnabled(true);
                    }
                });
    }
}
