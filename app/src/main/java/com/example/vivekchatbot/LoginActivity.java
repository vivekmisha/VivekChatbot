package com.example.vivekchatbot;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.example.vivekchatbot.databinding.ActivityLoginBinding;
import com.example.vivekchatbot.utility.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
    }

    public void doLogin(View view) {
        if (validateData()) {
            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
            String email = binding.email.getText().toString().trim();
            String password = binding.password.getText().toString().trim();
            firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Exception exception = task.getException();
                                System.out.println("Error creating user: " + exception.getMessage());
                            } else {
                                String userId = firebaseAuth.getCurrentUser().getUid();
                                SharedPrefernces sharedPrefernces = new SharedPrefernces(getApplicationContext());
                                sharedPrefernces.setValueString(Constants.USER_ID,userId);
                                sharedPrefernces.setValueBool(Constants.IS_LOGGED_IN,true);
                                // You can access the user's ID using userId
                                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                                finish();
                            }
                        }
                    });
        }
        else Toast.makeText(this, "filled the required fields  ", Toast.LENGTH_SHORT).show();
    }

    public void doReset(View view){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String emailAddress = binding.email.getText().toString();
        auth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // Password reset email sent successfully
                            Toast.makeText(getApplicationContext(), "Password reset email sent", Toast.LENGTH_SHORT).show();
                        } else {
                            // Password reset email failed to send
                            Toast.makeText(getApplicationContext(), "Failed to send password reset email", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }



    private boolean validateData() {
        return !binding.email.getText().toString().trim().isEmpty()
                && !binding.password.getText().toString().trim().isEmpty();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}