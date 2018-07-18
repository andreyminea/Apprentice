package ro.changeneers.apprentice.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.View;

import ro.changeneers.apprentice.Adapters.ViewPagerAdapter;
import ro.changeneers.apprentice.Fragments.FragmentBani;
import ro.changeneers.apprentice.Fragments.FragmentJobs;
import ro.changeneers.apprentice.Fragments.FragmentOverview;
import ro.changeneers.apprentice.Fragments.FragmentStartJourney;
import ro.changeneers.apprentice.Fragments.FragmentStres;
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


//        switch(position){
//            case 0:
////                mViewPager.setCurrentItem(0);
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FragmentOverview()).commitAllowingStateLoss();
//                bottomNav.setSelectedItemId(R.id.nav_info);
//                break;
//            case 1:
////                mViewPager.setCurrentItem(1);
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FragmentBani()).commitAllowingStateLoss();
//                bottomNav.setSelectedItemId(R.id.nav_bani);
//                break;
//            case 2:
////                mViewPager.setCurrentItem(2);
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FragmentStres()).commitAllowingStateLoss();
//                bottomNav.setSelectedItemId(R.id.nav_stres);
//                break;
//            case 3:
//                mViewPager.setCurrentItem(3);
////                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FragmentJobs()).commitAllowingStateLoss();
//                bottomNav.setSelectedItemId(R.id.nav_jobs);
//                break;
//        }

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
//                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                            selectedFragment).commitAllowingStateLoss();
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
