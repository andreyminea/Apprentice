package ro.changeneers.apprentice.Activities;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ro.changeneers.apprentice.Adapters.JourneyDetailsFragmentAdapter;
import ro.changeneers.apprentice.Fragments.FragmentBani;
import ro.changeneers.apprentice.Fragments.FragmentJobs;
import ro.changeneers.apprentice.Fragments.FragmentOverview;
import ro.changeneers.apprentice.Fragments.FragmentStartJourney;
import ro.changeneers.apprentice.Fragments.FragmentStres;
import ro.changeneers.apprentice.R;

public class JourneyViewPagerActivity extends AppCompatActivity {

    private static final String TAG = "JourneyCardStackActivit";

    private ViewPager jcsViewPager;
    private JourneyDetailsFragmentAdapter jcsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journey_card_stack);

        jcsViewPager = findViewById(R.id.ViewPagerStack);
        jcsAdapter = new JourneyDetailsFragmentAdapter(getSupportFragmentManager());
        setupJCSViewPager(jcsViewPager);
    }

    private void setupJCSViewPager(ViewPager viewPager){
        JourneyDetailsFragmentAdapter adapter = new JourneyDetailsFragmentAdapter(getSupportFragmentManager());
        adapter.addJCSFragment(new FragmentOverview(),"FragmentOverview");
        adapter.addJCSFragment(new FragmentBani(),"FragmentBani");
        adapter.addJCSFragment(new FragmentStres(),"FragmentStres");
        adapter.addJCSFragment(new FragmentJobs(),"FragmentJobs");
        adapter.addJCSFragment(new FragmentStartJourney(),"FragmentStartJourney");
       viewPager.setAdapter(adapter);
    }
}
