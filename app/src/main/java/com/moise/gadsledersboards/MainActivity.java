package com.moise.gadsledersboards;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.moise.gadsledersboards.fragments.LearningLeadersFragment;
import com.moise.gadsledersboards.adapters.SectionsPagerAdapter;
import com.moise.gadsledersboards.fragments.SkillIqLeadersFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    public static final int LEARNING_LEADERS_FRAGMENT = 0;
    public static final int SKILL_IQ_LEADERS_FRAGMENT = 1;
    //fragments
    private LearningLeadersFragment mLearningLeadersFragment;
    private SkillIqLeadersFragment mSkillIqLeadersFragment;
    //widgets
    private ViewPager mViewPager;
    private Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = findViewById(R.id.view_pager);
        setViewPager();
        mButton = findViewById(R.id.submit_btn);
        mButton.setOnClickListener(this);
    }

    private void setViewPager(){
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        mLearningLeadersFragment = new LearningLeadersFragment();
        mSkillIqLeadersFragment = new SkillIqLeadersFragment();
        sectionsPagerAdapter.addFragment(mLearningLeadersFragment);
        sectionsPagerAdapter.addFragment(mSkillIqLeadersFragment);
        mViewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(mViewPager);

        tabs.getTabAt(LEARNING_LEADERS_FRAGMENT).setText(getString(R.string.learning_leaders_fragment));
        tabs.getTabAt(SKILL_IQ_LEADERS_FRAGMENT).setText(getString(R.string.skill_iq_leaders_fragment));
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == (R.id.submit_btn)){
            Intent intent = new Intent(MainActivity.this, SubmissionActivity.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    }
}