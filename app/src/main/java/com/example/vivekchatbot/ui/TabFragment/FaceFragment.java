package com.example.vivekchatbot.ui.TabFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vivekchatbot.R;
import com.example.vivekchatbot.databinding.FragmentFaceBinding;
import com.example.vivekchatbot.databinding.FragmentSnapBinding;
import com.example.vivekchatbot.models.Face;
import com.example.vivekchatbot.recyclerChatAdapter.FaceAdapter;

import java.util.ArrayList;

public class FaceFragment extends Fragment {
    FragmentFaceBinding binding;
    ArrayList arrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFaceBinding.inflate(inflater, container, false);

        binding.recyclerView.setLayoutManager(new GridLayoutManager(requireContext(),3));




        arrayList = new ArrayList<>();

        arrayList.add(new Face(R.drawable.emoji_icon));
        arrayList.add(new Face(R.drawable.emoji_icon));
        arrayList.add(new Face(R.drawable.emoji_icon));
        arrayList.add(new Face(R.drawable.emoji_icon));
        arrayList.add(new Face(R.drawable.emoji_icon));
        arrayList.add(new Face(R.drawable.emoji_icon));
        arrayList.add(new Face(R.drawable.emoji_icon));
        arrayList.add(new Face(R.drawable.emoji_icon));
        arrayList.add(new Face(R.drawable.emoji_icon));
        arrayList.add(new Face(R.drawable.emoji_icon));
        arrayList.add(new Face(R.drawable.emoji_icon));
        arrayList.add(new Face(R.drawable.emoji_icon));
        arrayList.add(new Face(R.drawable.emoji_icon));
        arrayList.add(new Face(R.drawable.emoji_icon));
        arrayList.add(new Face(R.drawable.emoji_icon));
        arrayList.add(new Face(R.drawable.emoji_icon));

        FaceAdapter Adapter = new FaceAdapter(arrayList, requireContext());
        binding.recyclerView.setAdapter(Adapter);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}