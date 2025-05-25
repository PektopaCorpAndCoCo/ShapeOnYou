package com.example.shapeonyou;

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
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);

        authHelper        = new Login(this);
        etUsername        = findViewById(R.id.etUsername);
        etEmail           = findViewById(R.id.etEmail);
        etPassword        = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnSignUp         = findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(v -> {
            String username = etUsername.getText().toString().trim();
            String email    = etEmail.getText().toString().trim();
            String pass     = etPassword.getText().toString();
            String confirm  = etConfirmPassword.getText().toString();

            if (!pass.equals(confirm)) {
                Toast.makeText(this, "Les mots de passe ne correspondent pas", Toast.LENGTH_SHORT).show();
                return;
            }

            authHelper.signUp(email, pass, (OnCompleteListener<AuthResult>) task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(this, "Inscription réussie ! Connectez-vous.", Toast.LENGTH_SHORT).show();
                    finish();  // Retour à LoginActivity
                } else {
                    Toast.makeText(this,
                            "Erreur d’inscription : " + task.getException().getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            });
        });
    }
}
