package com.example.vivekchatbot.ui.gallery;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.vivekchatbot.databinding.FragmentCameraRollBinding;

public class CameraRollFragment extends Fragment {
    FragmentCameraRollBinding binding;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCameraRollBinding.inflate(inflater, container, false);
        return (binding.getRoot());

    }
}