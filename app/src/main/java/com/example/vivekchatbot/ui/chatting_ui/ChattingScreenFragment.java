package com.example.vivekchatbot.ui.chatting_ui;

import static android.service.controls.ControlsProviderService.TAG;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vivekchatbot.LoginActivity;
import com.example.vivekchatbot.SignUpActivity;
import com.example.vivekchatbot.databinding.FragmentChattingScreenBinding;
import com.example.vivekchatbot.models.ChatMessage;
import com.example.vivekchatbot.recyclerChatAdapter.ChattingBoxAdapter;
import com.example.vivekchatbot.ui.chat.ChatFragment;
import com.example.vivekchatbot.ui.gallery.SnapFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChattingScreenFragment extends Fragment {
   List<ChatMessage> chatMessages;
    FragmentChattingScreenBinding binding;
    String senderRoom, receiverRoom;
    FirebaseAuth auth;


    public ChattingScreenFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentChattingScreenBinding.inflate(inflater,container,false);
        auth = FirebaseAuth.getInstance();
        String message = binding.etTextMessage.getText().toString().trim();

        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("User").get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful())
                                {
                                    for (QueryDocumentSnapshot queryDocumentSnapshot: task.getResult()){

                                    }
                                }
                            }
                        });

        binding.icSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(binding.etTextMessage.getText().toString().isEmpty()))
                {
                    senderRoom = auth.getUid();

                }
            }
        });

        binding.recyclerGchat.setLayoutManager(new LinearLayoutManager(requireContext()));


        chatMessages = new ArrayList<>();
        chatMessages.add(new ChatMessage(1,"hello ",false,"Sumit  "));
        chatMessages.add(new ChatMessage(1,"hello vivek ",true,""));
        chatMessages.add(new ChatMessage(2,"hello sumit ",false,""));
        chatMessages.add(new ChatMessage(2,"How are you ",true,""));
        chatMessages.add(new ChatMessage(1,"I am good and you ",false,""));
        chatMessages.add(new ChatMessage(1,"How are you ",true,""));
        ChattingBoxAdapter chattingBoxAdapter= new ChattingBoxAdapter(requireContext(),chatMessages);
        binding.recyclerGchat.setAdapter(chattingBoxAdapter);

        return binding.getRoot();
    }
}