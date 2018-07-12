package ro.changeneers.apprentice;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class JourneyCardStackAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> jcsFragmentList = new ArrayList<>();
    private final List<String> jcsFragmentTitleList = new ArrayList<>();

    public JourneyCardStackAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addJCSFragment(Fragment fragment, String title){
        jcsFragmentList.add(fragment);
        jcsFragmentTitleList.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        return jcsFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return jcsFragmentList.size();
    }
}
