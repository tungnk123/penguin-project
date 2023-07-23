package com.example.penguin_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.penguin_project.view.fragment.MenuFragment;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity {
    private TextView textView;
    private GoogleSignInClient client;

    ImageView googleBtn;
    ImageButton btnBack;
    String default_web_client_id = "54129121017-ellud7jc5ecupa7k7492bauf842pu42j.apps.googleusercontent.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        googleBtn = findViewById(R.id.google_btn);
        btnBack = findViewById(R.id.btn_activityLogin_backButton);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("SELECTED_FRAGMENT", "setting");
                startActivity(intent);
            }
        });


        GoogleSignInOptions options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(default_web_client_id)
                .requestEmail()
                .build();
        client = GoogleSignIn.getClient(this,options);


        googleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = client.getSignInIntent();
                startActivityForResult(i,1234);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1234){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);

                AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(),null);
                FirebaseAuth.getInstance().signInWithCredential(credential)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){

                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                                    updateUI(user);
                                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                    intent.putExtra("SELECTED_FRAGMENT", "setting");
                                    startActivity(intent);

                                }else {
                                    Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

            } catch (ApiException e) {
                e.printStackTrace();
            }

        }

    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user!= null){
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            intent.putExtra("SELECTED_FRAGMENT", "setting");
            startActivity(intent);
        }
    }
    private void updateUI(FirebaseUser user) {
        if (user != null) {
            // User is logged in, update the UI accordingly
            String displayName = user.getDisplayName();
            String email = user.getEmail();
            Uri uri = user.getPhotoUrl();
            SharedPreferences.Editor editor = MenuFragment.userSP.edit();
            editor.putString("username", displayName);
            editor.putString("email", email);
            editor.putString("uri", uri.toString());
            editor.apply();
            backUpData();
            finish();
        } else {
            // User is not logged in, handle the UI accordingly
            // For example, show a login button or prompt the user to sign in.
        }
    }

    private void backUpData() {
        Toast.makeText(this, "Backed up successfully!", Toast.LENGTH_SHORT).show();
    }
}