package com.example.vivekchatbot.recyclerChatAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.vivekchatbot.ui.TabFragment.FaceFragment;
import com.example.vivekchatbot.ui.TabFragment.MusicFragment;
import com.example.vivekchatbot.ui.TabFragment.TrendingFragment;
import com.example.vivekchatbot.ui.TabFragment.WorldFragment;


public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position==0) {
            return new TrendingFragment();
        }
        else if (position ==1) {
            return new FaceFragment() ;
        } else if (position==2) {
            return new MusicFragment();

    } else return new WorldFragment();
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
