package com.example.vivekchatbot.ui.chat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vivekchatbot.R;
import com.example.vivekchatbot.databinding.FragmentDashboardBinding;
import com.example.vivekchatbot.models.Chat;
import com.example.vivekchatbot.recyclerChatAdapter.ChatAdapter;

import java.util.ArrayList;

public class ChatFragment extends Fragment {

    private FragmentDashboardBinding binding;
   Button btn;
    ArrayList arrayList;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ChatViewModel dashboardViewModel =
                new ViewModelProvider(this).get(ChatViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();







        binding.recyclerChat.setLayoutManager(new LinearLayoutManager(requireContext()));


        arrayList = new ArrayList<>();


        arrayList.add(new Chat(R.drawable.baseline_person_24,"Vivek Mishra","my name is vivek mishra",R.drawable.emoji_icon , R.drawable.chat));
        arrayList.add(new Chat(R.drawable.baseline_person_24,"Deepak","how r you.. ?",R.drawable.emoji_icon , R.drawable.chat));
        arrayList.add(new Chat(R.drawable.baseline_person_24,"Pawan","Hii vivek ,Good morning",R.drawable.emoji_icon , R.drawable.chat));
        arrayList.add(new Chat(R.drawable.baseline_person_24,"Sumit69","hii sumit, good night",R.drawable.emoji_icon , R.drawable.chat));
        arrayList.add(new Chat(R.drawable.baseline_person_24,"Vivek","hello divyanshu ",R.drawable.emoji_icon , R.drawable.chat));
        arrayList.add(new Chat(R.drawable.baseline_person_24,"Aman , Shivam ,Abhishek, Vaibhav","message",R.drawable.emoji_icon , R.drawable.chat));

        ChatAdapter chatAdapter = new ChatAdapter(requireContext(), arrayList);
        binding.recyclerChat.setAdapter(chatAdapter);



        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;

    }
}