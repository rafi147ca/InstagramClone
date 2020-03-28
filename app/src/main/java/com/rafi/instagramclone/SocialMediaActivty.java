package com.rafi.instagramclone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;

public class SocialMediaActivty extends AppCompatActivity {

    private androidx.appcompat.widget.Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private  TabAdaptor tabAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_media_activty);
        setTitle("Social Media App");
        toolbar=findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);
        viewPager= findViewById(R.id.viewPager);
        tabAdaptor=new TabAdaptor(getSupportFragmentManager());
        viewPager.setAdapter(tabAdaptor);
        tabLayout=findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager,false);
    }
}
