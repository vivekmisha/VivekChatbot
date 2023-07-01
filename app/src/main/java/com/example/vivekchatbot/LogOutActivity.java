package com.example.vivekchatbot;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.vivekchatbot.databinding.ActivityLogOutBinding;
import com.example.vivekchatbot.utility.Constants;
import com.google.firebase.auth.FirebaseAuth;

public class LogOutActivity extends AppCompatActivity {
ActivityLogOutBinding binding;
FirebaseAuth firebaseAuth;
Context context=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_out);
        binding = ActivityLogOutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
         binding.logoutButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 openDialog();

             }

             private void openDialog() {

                 Dialog dialog = new Dialog(context);
                 dialog.setContentView(R.layout.custom_logout_dialog);
                 dialog.show();
                 dialog.findViewById(R.id.btnreset).setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         firebaseAuth = FirebaseAuth.getInstance();
                         firebaseAuth.signOut();
                         SharedPrefernces sharedPrefernces=new SharedPrefernces(context);
                         sharedPrefernces.setValueBool(Constants.IS_LOGGED_IN,false);
                         startActivity(new Intent(context,SignUpActivity.class));
                         finish();
                     }
                 });
                 dialog.findViewById(R.id.btncancel).setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         dialog.dismiss();
                     }
                 });
             }
         });



    }
//    public void doLogout(View view) {
//
//        firebaseAuth = FirebaseAuth.getInstance();
//        firebaseAuth.signOut();
//
//        binding.logoutButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                firebaseAuth.signOut();
//                SharedPrefernces sharedPrefernces=new SharedPrefernces(context);
//                sharedPrefernces.setValueBool(Constants.IS_LOGGED_IN,false);
//                startActivity(new Intent(context,SignUpActivity.class));
//                finish();
//            }
//        });
//
//    }


}