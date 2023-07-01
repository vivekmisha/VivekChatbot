package com.example.vivekchatbot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.vivekchatbot.databinding.ActivitySignUpBinding;
import com.example.vivekchatbot.utility.Constants;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {
    ActivitySignUpBinding binding;
    String email, password, firstName, lastName, phoneNumber, repeatPass;
    String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FirebaseApp.initializeApp(this);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);


    }

    public void saveData(View view) {
        String email = binding.emailId.getText().toString();
        String password = binding.passwordId.getText().toString();
        if (validateData()) {
            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Log.d(TAG, "onComplete: " + task.isSuccessful());
                                // User creation failed
                                Exception exception = task.getException();
                                // Handle the exception
                            } else {
                                Log.d("TAG", "onComplete: success");
                                // User creation successful
                                String userId = firebaseAuth.getCurrentUser().getUid();
                                SharedPrefernces sharedPrefernces = new SharedPrefernces(getApplicationContext());
                                sharedPrefernces.setValueString(Constants.USER_ID, userId);
                                sharedPrefernces.setValueBool(Constants.IS_LOGGED_IN, true);

                                FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
                                DocumentReference collectionReference = firebaseFirestore.collection("User").document(binding.phonNumber.getText().toString().trim());
                                HashMap<String, Object> map = new HashMap<>();
                                map.put("Email", binding.firstName.getText().toString().trim());
                                map.put("LastName", binding.lastName.getText().toString().trim());
                                map.put("uid", firebaseAuth.getUid());
                                map.put("phoneNumber", binding.phonNumber.getText().toString().trim());
                                collectionReference.set(map)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Log.d(TAG, "onComplete: " + "Data saved");
                                                    // You can access the user's ID using userId
                                                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                                    Toast.makeText(SignUpActivity.this, "tq", Toast.LENGTH_SHORT).show();
                                                    finish();

                                                }
                                            }

                                        }).
                                        addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.d("TAG", "onFailure: " + e);

                                            }
                                        })
                                                .

                                        addOnCanceledListener(new OnCanceledListener() {
                                            @Override
                                            public void onCanceled() {
                                                Log.d("TAG", "onCanceled: ");

                                            }
                                        });
                            }
                        }
                    });
        }
    }

    private boolean validateData() {
        if (!binding.emailId.getText().toString().trim().isEmpty()
                && !binding.passwordId.getText().toString().trim().isEmpty()
                && !binding.firstName.getText().toString().trim().isEmpty()
                && !binding.lastName.getText().toString().trim().isEmpty()
                && !binding.phonNumber.getText().toString().trim().isEmpty()
                && !binding.cnfPasswordId.getText().toString().trim().isEmpty()) {
            email = binding.emailId.getText().toString().trim();
            password = binding.passwordId.getText().toString().trim();
            repeatPass = binding.cnfPasswordId.getText().toString().trim();
            firstName = binding.firstName.getText().toString().trim();
            lastName = binding.lastName.getText().toString().trim();
            phoneNumber = binding.phonNumber.getText().toString().trim();

            if (phoneNumber.length() < 10) {
                Toast.makeText(this, "Enter valid Phone number", Toast.LENGTH_SHORT).show();
                return false;
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Enter Valid Email Address", Toast.LENGTH_SHORT).show();
                return false;
            } else if (!password.equals(repeatPass)) {
                Toast.makeText(this, "Password Not matched", Toast.LENGTH_SHORT).show();
                return false;

            } else if (!(password.length() > 8)) {
                Toast.makeText(this, "enter valid password", Toast.LENGTH_SHORT).show();
                return false;
            } else if (!password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")) {
                Toast.makeText(this, "\"Password should contain at least one digit, one lowercase and uppercase letter, one special character, and no whitespaces", Toast.LENGTH_SHORT).show();
                return false;
            } else return true;

        } else {
            Toast.makeText(this, "fields are required", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public void goToLogin(View view) {
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }

}
