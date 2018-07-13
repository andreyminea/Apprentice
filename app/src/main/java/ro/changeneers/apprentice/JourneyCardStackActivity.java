package ro.changeneers.apprentice;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
        jcsViewPager.setPageTransformer(false,new JourneyCardStackTransformer());
        jcsViewPager.setOffscreenPageLimit(5);
        setupJCSViewPager(jcsViewPager);
    }

    private void setupJCSViewPager(ViewPager viewPager){
        JourneyCardStackAdapter adapter = new JourneyCardStackAdapter(getSupportFragmentManager());
        adapter.addJCSFragment(new FragmentOverview(),"FragmentOverview");
        adapter.addJCSFragment(new FragmentBani(),"FragmentBani");
        adapter.addJCSFragment(new FragmentStres(),"FragmentStres");
        adapter.addJCSFragment(new FragmentJobs(),"FragmentJobs");
        adapter.addJCSFragment(new FragmentStartJourney(),"FragmentStartJourney");

        viewPager.setAdapter(adapter);
    }
}
