package com.example.penguin_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.penguin_project.model.repo.local.DataBase.HabitDataBase;
import com.example.penguin_project.model.repo.local.Table.Habits;
import com.example.penguin_project.model.repo.local.Table.StoreItem;
import com.example.penguin_project.model.repo.local.Table.Todo;
import com.example.penguin_project.model.repo.remote.FirebaseUserHelper;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
            getDataFromFirebase();
            backUpData();
            finish();
        } else {
            // User is not logged in, handle the UI accordingly
            // For example, show a login button or prompt the user to sign in.
        }
    }

    private void getDataFromFirebase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("backup");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        FirebaseUserHelper userHelper = dataSnapshot.getValue(FirebaseUserHelper.class);
                        Toast.makeText(LoginActivity.this, String.valueOf(userHelper.habitMapList.size()), Toast.LENGTH_SHORT).show();

                        for (HashMap<String, Object> habitMap : userHelper.habitMapList) {
                            int habit_id = ((Long) habitMap.get("Habit_id")).intValue(); // Convert Long to int
                            String title = (String) habitMap.get("Title");
                            int timeOfDay_id = ((Long) habitMap.get("TimeOfDay_id")).intValue(); // Convert Long to int
                            int timePerDay = ((Long) habitMap.get("TimePerDay")).intValue(); // Convert Long to int
                            int color = ((Long) habitMap.get("Color")).intValue(); // Convert Long to int
                            int icon = ((Long) habitMap.get("Icon")).intValue(); // Convert Long to int
                            LocalDate createDay = LocalDate.parse((String) habitMap.get("CreateDay"));
                            int currentStreak = ((Long) habitMap.get("CurrentStreak")).intValue(); // Convert Long to int
                            int maxStreak = ((Long) habitMap.get("MaxStreak")).intValue(); // Convert Long to int
                            int tree_id = ((Long) habitMap.get("Tree_id")).intValue(); // Convert Long to int

                            Habits habit = new Habits(title, timeOfDay_id, timePerDay, color, icon, createDay, currentStreak, maxStreak, tree_id);
                            habit.setHabit_id(habit_id);
                            HabitDataBase.getInstance(getApplicationContext()).habitDAO().insertHabit(habit);

                        }
                        for (HashMap<String, Object> storeItemMap : userHelper.storeItemMapList) {
                            int item_id = ((Long) storeItemMap.get("Item_id")).intValue(); // Convert Long to int
                            String itemName = (String) storeItemMap.get("ItemName");
                            int itemPrice = ((Long) storeItemMap.get("ItemPrice")).intValue(); // Convert Long to int
                            int itemImg = ((Long) storeItemMap.get("ItemImg")).intValue(); // Convert Long to int
                            String description = (String) storeItemMap.get("Description");
                            String storeItemType = (String) storeItemMap.get("StoreItemType");
                            boolean isPurchased = (boolean) storeItemMap.get("IsPurchased");
                            Toast.makeText(LoginActivity.this, itemName, Toast.LENGTH_SHORT).show();

//                            StoreItem storeItem = new StoreItem(itemName, itemPrice, itemImg, description, storeItemType, isPurchased);
                            HabitDataBase.getInstance(getApplicationContext()).habitDAO().updateIsPurchasedById(item_id, isPurchased);
                        }

                    }
                } catch (Exception ex) {
                    Toast.makeText(LoginActivity.this, "Get data failed!", Toast.LENGTH_SHORT).show();
                    Log.d("ERRRRRRRRRRRRRRRRRRR", ex.getMessage());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void backUpData() {
        Toast.makeText(this, "Backed up successfully!", Toast.LENGTH_SHORT).show();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("backup");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String username = user.getDisplayName();
            String email = user.getEmail();
            List<Habits> habitsList = HabitDataBase.getInstance(this).habitDAO().getHabitList();
            List<HashMap<String, Object>> habitMapList = new ArrayList<>();

            for (Habits habit : habitsList) {
                HashMap<String, Object> habitMap = new HashMap<>();
                habitMap.put("Habit_id", habit.getHabit_id());
                habitMap.put("Title", habit.getTitle());
                habitMap.put("TimeOfDay_id", habit.getTimeOfDay_id());
                habitMap.put("TimePerDay", habit.getTimePerDay());
                habitMap.put("Color", habit.getColor());
                habitMap.put("Icon", habit.getIcon());
                habitMap.put("CreateDay", habit.getCreateDay().toString()); // Convert LocalDate to String
                habitMap.put("CurrentStreak", habit.getCurrentStreak());
                habitMap.put("MaxStreak", habit.getMaxStreak());
                habitMap.put("Tree_id", habit.getTree_id());
                habitMapList.add(habitMap);
            }

            List<StoreItem> storeItemList = HabitDataBase.getInstance(this).habitDAO().getStoreItemByTypeNotLiveData("tree");
            List<HashMap<String, Object>> storeItemMapList = new ArrayList<>();

            for (StoreItem storeItem : storeItemList) {
                HashMap<String, Object> storeItemMap = new HashMap<>();
                storeItemMap.put("Item_id", storeItem.getItem_id());
                storeItemMap.put("ItemName", storeItem.getItemName());
                storeItemMap.put("ItemPrice", storeItem.getItemPrice());
                storeItemMap.put("ItemImg", storeItem.getItemImg());
                storeItemMap.put("Description", storeItem.getDescription());
                storeItemMap.put("StoreItemType", storeItem.getStoreItemType());
                storeItemMap.put("IsPurchased", storeItem.getIsPurchased());

                storeItemMapList.add(storeItemMap);
            }

            FirebaseUserHelper userHelper = new FirebaseUserHelper(username, email, habitMapList, storeItemMapList);
            myRef.child(username).setValue(userHelper, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                    if (error != null) {
                        // Handle any error occurred while setting the value
                        Log.e("FirebaseError", error.getMessage());
                    } else {
                        Log.d("FirebaseSuccess", "Data backup successful");
                    }
                }
            });
        }
    }

}