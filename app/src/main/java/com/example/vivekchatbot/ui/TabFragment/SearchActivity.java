package com.example.vivekchatbot.ui.TabFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.vivekchatbot.R;
import com.example.vivekchatbot.databinding.ActivitySearchBinding;
import com.example.vivekchatbot.recyclerChatAdapter.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class SearchActivity extends AppCompatActivity {
    ActivitySearchBinding binding;
    TabLayout tabs;
    ViewPager2 viewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        tabs = binding.tabs;
        viewPager2 = binding.viewPager2;

        TabLayout.Tab FaceTab = tabs.newTab();
        FaceTab.setText("Face");
        TabLayout.Tab TrendingTab = tabs.newTab();
        TrendingTab.setText("Trending");

        TabLayout.Tab WorldTab = tabs.newTab();
        WorldTab.setText("World");
        TabLayout.Tab MusicTab = tabs.newTab();
        MusicTab.setText("Music");


        tabs.addTab(FaceTab);
        tabs.addTab(TrendingTab,false);
        tabs.addTab(MusicTab,true);
        tabs.addTab(WorldTab);
        tabs.setSelectedTabIndicatorColor(getResources().getColor(R.color.black));

        tabs.setTabTextColors(getResources().getColor(R.color.black),getResources().getColor(R.color.black));

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),getLifecycle());
        viewPager2.setAdapter(viewPagerAdapter);
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabs.selectTab(tabs.getTabAt(position));
            }
        });



    }
}