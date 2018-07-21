package ro.changeneers.apprentice.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import ro.changeneers.apprentice.adapters.ViewPagerAdapter;
import ro.changeneers.apprentice.fragments.FragmentBani;
import ro.changeneers.apprentice.fragments.FragmentJobs;
import ro.changeneers.apprentice.fragments.FragmentOverview;
import ro.changeneers.apprentice.fragments.FragmentStartJourney;
import ro.changeneers.apprentice.fragments.FragmentStres;
import ro.changeneers.apprentice.R;

public class BottomNavigationActivity extends NavDrawer {

    private ViewPagerAdapter mViewPagerAdapter;
    private ViewPager mViewPager;
    private MenuItem prevMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);

        mViewPager = (ViewPager) findViewById(R.id.fragment_container);
        final BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);


        Intent receivingIntent = getIntent();
        int position = receivingIntent.getIntExtra("position",0);
        setupViewPager(mViewPager,position,bottomNav);



        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                } else {
                    bottomNav.getMenu().getItem(0).setChecked(false);
                }
                bottomNav.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNav.getMenu().getItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch(item.getItemId()){
                        case R.id.nav_info:
                            mViewPager.setCurrentItem(0);
                            break;
                        case R.id.nav_bani:
                            mViewPager.setCurrentItem(1);
                            break;
                        case R.id.nav_stres:
                            mViewPager.setCurrentItem(2);
                            break;
                        case R.id.nav_jobs:
                            mViewPager.setCurrentItem(3);
                            break;
                        case R.id.nav_start:
                            mViewPager.setCurrentItem(4);
                            break;
                    }

                    return true;
                }
            };

    private void setupViewPager(ViewPager viewpager,int position,BottomNavigationView bnv){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        FragmentOverview fov = new FragmentOverview();
        FragmentBani fb = new FragmentBani();
        FragmentStres fs = new FragmentStres();
        FragmentJobs fj = new FragmentJobs();
        FragmentStartJourney fsj = new FragmentStartJourney();
        adapter.addFragment(fov);
        adapter.addFragment(fb);
        adapter.addFragment(fs);
        adapter.addFragment(fj);
        adapter.addFragment(fsj);
        mViewPager.setAdapter(adapter);
        viewpager.setCurrentItem(position);
        switch (position){
            case 0:
                bnv.setSelectedItemId(R.id.nav_info);
                break;
            case 1:
                bnv.setSelectedItemId(R.id.nav_bani);
                break;
            case 2:
                bnv.setSelectedItemId(R.id.nav_stres);
                break;
            case 3:
                bnv.setSelectedItemId(R.id.nav_jobs);
                break;
        }
    }
}
