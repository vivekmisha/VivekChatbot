package com.example.vivekchatbot.ui.gallery;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.vivekchatbot.databinding.FragmentSnapBinding;
import com.example.vivekchatbot.recyclerChatAdapter.GalleryAdapter;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class SnapFragment extends Fragment {
    FragmentSnapBinding binding;
    List<File> imageList;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSnapBinding.inflate(inflater, container, false);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(requireContext(),3));


        GalleryAdapter adapter = new GalleryAdapter(fetchImageListFromStorage() ,requireContext());
        Collections.reverse(imageList);
        binding.recyclerView.setAdapter(adapter);

        return binding.getRoot();

    }

    private ArrayList fetchImageListFromStorage() {
         imageList = new ArrayList<>();



        // Get the directory for the saved bitmaps
        File directory = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "SnapchatLite");

        // Check if the directory exists
        if (directory.exists()) {
            // Get all files in the directory
            File[] files = directory.listFiles();

            // Filter the files to include only image files
            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && isImageFile(file.getName())) {
                        imageList.add(file);
                    }
                }
            }
        }

        return  (ArrayList) imageList;
    }

    private boolean isImageFile(String fileName) {
        // Check if the file has an image extension
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
        return extension.equalsIgnoreCase("jpg") ||
                extension.equalsIgnoreCase("jpeg") ||
                extension.equalsIgnoreCase("png");
    }

}