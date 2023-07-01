package com.example.vivekchatbot.recyclerChatAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.vivekchatbot.ui.gallery.CameraRollFragment;
import com.example.vivekchatbot.ui.gallery.SnapFragment;

public class Gallery_viewPagerAdapter extends FragmentStateAdapter {

    public Gallery_viewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new SnapFragment();

        } else return new CameraRollFragment() ;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
