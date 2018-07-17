package ro.changeneers.apprentice.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import ro.changeneers.apprentice.Fragments.FragmentBani;
import ro.changeneers.apprentice.Fragments.FragmentJobs;
import ro.changeneers.apprentice.Fragments.FragmentOverview;
import ro.changeneers.apprentice.Fragments.FragmentStartJourney;
import ro.changeneers.apprentice.Fragments.FragmentStres;
import ro.changeneers.apprentice.R;

public class BottomNavigationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        Intent receivingIntent = getIntent();
        int position = receivingIntent.getIntExtra("position",0);
        switch(position){
            case 0:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FragmentOverview()).commit();
                bottomNav.setSelectedItemId(R.id.nav_info);
                break;
            case 1:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FragmentBani()).commit();
                bottomNav.setSelectedItemId(R.id.nav_bani);
                break;
            case 2:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FragmentStres()).commit();
                bottomNav.setSelectedItemId(R.id.nav_stres);
                break;
            case 3:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new FragmentJobs()).commit();
                bottomNav.setSelectedItemId(R.id.nav_jobs);
                break;
        }

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch(item.getItemId()){
                        case R.id.nav_info:
                            selectedFragment = new FragmentOverview();
                            break;
                        case R.id.nav_bani:
                            selectedFragment = new FragmentBani();
                            break;
                        case R.id.nav_stres:
                            selectedFragment = new FragmentStres();
                            break;
                        case R.id.nav_jobs:
                            selectedFragment = new FragmentJobs();
                            break;
                        case R.id.nav_start:
                            selectedFragment = new FragmentStartJourney();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                    return true;
                }
            };
}
