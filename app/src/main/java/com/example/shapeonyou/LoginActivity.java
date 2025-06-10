package com.example.shapeonyou;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;  // Forgot Password est un TextView
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;

public class LoginActivity extends AppCompatActivity {

    private Login authHelper;
    private EditText etEmail, etPassword;
    private Button btnLogin, btnSignUp;
    private TextView btnForgot;  // Forgot Password

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        authHelper = new Login(this);

        etEmail    = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin   = findViewById(R.id.btnLogin);
        btnForgot  = findViewById(R.id.btnForgot);
        btnSignUp  = findViewById(R.id.btnSignUp);

        btnLogin.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String pwd   = etPassword.getText().toString();
            if (email.isEmpty() && pwd.isEmpty()) {
                Toast.makeText(this, "Veuillez entrer votre email et votre mot de passe", Toast.LENGTH_SHORT).show();
                return;
            }
            authHelper.signIn(email, pwd, task -> {

                if (task.isSuccessful()) {
                    startActivity(new Intent(this, MainActivity.class));
                    finish();

                }
                else {
                    Toast.makeText(this,
                            "Échec de la connexion : " + task.getException().getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            });
        });


        btnSignUp.setOnClickListener(v ->
                startActivity(new Intent(this, SignupActivity.class))
        );

        btnForgot.setOnClickListener(v -> {
            // Action pour le mot de passe oublié
            String email = etEmail.getText().toString().trim();
            if (email.isEmpty()) {
                Toast.makeText(this, "Veuillez entrer votre email", Toast.LENGTH_SHORT).show();
                return;
            }
            authHelper.resetPassword(email, task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(this, "Email de réinitialisation envoyé", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Erreur : " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        });
    }
}
