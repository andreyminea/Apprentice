package ro.changeneers.apprentice.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ro.changeneers.apprentice.activities.JourneyActivity;
import ro.changeneers.apprentice.R;

public class FragmentStartJourney extends android.support.v4.app.Fragment {

    private static final String TAG = "FragmentStartJourney";

    private Button startJourneyBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_start_journey_layout,container,false);
        startJourneyBtn = view.findViewById(R.id.ButtonStartJourney);

        startJourneyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),JourneyActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
