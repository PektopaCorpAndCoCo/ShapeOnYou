
package com.example.shapeonyou;

import android.app.Activity;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login {

    private final FirebaseAuth mAuth;
    private final Activity activity;

    public Login(Activity activity) {
        this.activity = activity;
        mAuth = FirebaseAuth.getInstance();
    }

    /**
     * Inscrit un nouvel utilisateur avec email et mot de passe.
     */
    public void signUp(String email, String password, OnCompleteListener<AuthResult> listener) {
        mAuth.createUserWithEmailAndPassword(email.trim(), password)
                .addOnCompleteListener(activity, listener);
    }

    /**
     * Authentifie un utilisateur existant avec email et mot de passe.
     */
    public void signIn(String email, String password, OnCompleteListener<AuthResult> listener) {
        mAuth.signInWithEmailAndPassword(email.trim(), password)
                .addOnCompleteListener(activity, listener);
    }

    /**
     * Envoie un email de réinitialisation de mot de passe.
     */
    public void resetPassword(String email, OnCompleteListener<Void> listener) {
        mAuth.sendPasswordResetEmail(email.trim())
                .addOnCompleteListener(activity, listener);
    }

    /**
     * Déconnecte l'utilisateur courant.
     */
    public void signOut() {
        mAuth.signOut();
        Toast.makeText(activity, "Déconnecté", Toast.LENGTH_SHORT).show();
    }

    /**
     * Retourne l'utilisateur actuellement connecté, ou null.
     */
    public FirebaseAuth getCurrentUser() {
        return mAuth;
    }
}
