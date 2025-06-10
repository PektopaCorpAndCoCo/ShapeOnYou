package com.example.shapeonyou;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;

public class SignupActivity extends AppCompatActivity {
    private Login authHelper;
    private EditText etUsername, etEmail, etPassword, etConfirmPassword;
    private Button btnCreateAccount, btnLoginLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);

        authHelper        = new Login(this);
        etUsername        = findViewById(R.id.etUsername);
        etEmail           = findViewById(R.id.etEmail);
        etPassword        = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnCreateAccount  = findViewById(R.id.btnCreateAccount);
        btnLoginLink      = findViewById(R.id.btnLoginLink);

        btnCreateAccount.setOnClickListener(v -> {
            String username = etUsername.getText().toString().trim();
            String email    = etEmail.getText().toString().trim();
            String pass     = etPassword.getText().toString();
            String confirm  = etConfirmPassword.getText().toString();

            if (username.isEmpty() || email.isEmpty() || pass.isEmpty() || confirm.isEmpty()) {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Veuillez entrer un email valide", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!pass.equals(confirm)) {
                Toast.makeText(this, "Les mots de passe ne correspondent pas", Toast.LENGTH_SHORT).show();
                return;
            }

            authHelper.signUp(email, pass, (OnCompleteListener<AuthResult>) task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(this, "Inscription réussie ! Connectez-vous.", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this,
                            "Erreur d’inscription : " + task.getException().getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            });
        });

        btnLoginLink.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
    }
}