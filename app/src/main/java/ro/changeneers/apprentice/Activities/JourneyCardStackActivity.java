package ro.changeneers.apprentice.Activities;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ro.changeneers.apprentice.Adapters.JourneyCardStackAdapter;
import ro.changeneers.apprentice.Fragments.FragmentBani;
import ro.changeneers.apprentice.Fragments.FragmentJobs;
import ro.changeneers.apprentice.Fragments.FragmentOverview;
import ro.changeneers.apprentice.Fragments.FragmentStartJourney;
import ro.changeneers.apprentice.Fragments.FragmentStres;
import ro.changeneers.apprentice.JourneyCardStackTransformer;
import ro.changeneers.apprentice.R;

public class JourneyCardStackActivity extends AppCompatActivity {

    private static final String TAG = "JourneyCardStackActivit";

    private ViewPager jcsViewPager;
    private JourneyCardStackAdapter jcsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journey_card_stack);

        jcsViewPager = findViewById(R.id.ViewPagerStack);
        jcsAdapter = new JourneyCardStackAdapter(getSupportFragmentManager());
        setupJCSViewPager(jcsViewPager);
        jcsViewPager.setPageTransformer(false,new JourneyCardStackTransformer());
        jcsViewPager.setOffscreenPageLimit(5);
    }

    private void setupJCSViewPager(ViewPager viewPager){
        JourneyCardStackAdapter adapter = new JourneyCardStackAdapter(getSupportFragmentManager());
//        adapter.addJCSFragment(new FragmentOverview(),"FragmentOverview");
//        adapter.addJCSFragment(new FragmentBani(),"FragmentBani");
//        adapter.addJCSFragment(new FragmentStres(),"FragmentStres");
//        adapter.addJCSFragment(new FragmentJobs(),"FragmentJobs");
        adapter.addJCSFragment(new FragmentStartJourney(),"FragmentStartJourney");
        adapter.addJCSFragment(new FragmentJobs(),"FragmentJobs");
        adapter.addJCSFragment(new FragmentStres(),"FragmentStres");
        adapter.addJCSFragment(new FragmentBani(),"FragmentBani");
        adapter.addJCSFragment(new FragmentOverview(),"FragmentOverview");





        viewPager.setAdapter(adapter);
    }
}
