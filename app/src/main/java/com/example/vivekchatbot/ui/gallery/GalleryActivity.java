package com.example.vivekchatbot.ui.gallery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.vivekchatbot.R;
import com.example.vivekchatbot.databinding.ActivityGalleryBinding;
import com.example.vivekchatbot.recyclerChatAdapter.Gallery_viewPagerAdapter;
import com.example.vivekchatbot.recyclerChatAdapter.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class GalleryActivity extends AppCompatActivity {
    ActivityGalleryBinding binding ;
    ViewPager2 viewPager2;
    TabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        binding = ActivityGalleryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

      tabs= binding.tabs;
      viewPager2 = binding.viewPager2;

        TabLayout.Tab SnapTab = tabs.newTab();
        SnapTab.setText("Snap");
        TabLayout.Tab CameraRollTab = tabs.newTab();
        CameraRollTab.setText("Camera");

        tabs.addTab(SnapTab);

        tabs.addTab(CameraRollTab,true);
        tabs.setSelectedTabIndicatorColor(getResources().getColor(R.color.black));

        tabs.setTabTextColors(getResources().getColor(R.color.black),getResources().getColor(R.color.black));

        Gallery_viewPagerAdapter gallery_viewPagerAdapter = new Gallery_viewPagerAdapter(getSupportFragmentManager(),getLifecycle());
        viewPager2.setAdapter(gallery_viewPagerAdapter);

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